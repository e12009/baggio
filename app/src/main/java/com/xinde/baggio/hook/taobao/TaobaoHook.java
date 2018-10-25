package com.xinde.baggio.hook.taobao;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.xinde.baggio.hook.AbstractHook;

import dalvik.system.DexClassLoader;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/8/16 16:58
 * desc  :
 * update: Shawn 2018/8/16 16:58
 */
public class TaobaoHook extends AbstractHook {

    private static final String TAG = "TaobaoHook";
    @SuppressLint("SdCardPath")
    private static final String DATA_PATH = "/data/data/com.taobao.taobao";

    public TaobaoHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        Log.i(TAG, "run: ");

        hookPopLayer();
        hookUpdateThread();
    }

    /**
     * hook 首页活动弹窗
     * 双十一弹窗 url 形如： https://pages.tmall.com/wow/lafite/act/18-opening-poplayer?spm=a1z8m.7980655#http%3A%2F%2Fm.taobao.com%2Findex.htm
     */
    private void hookPopLayer() {
        try {
            XposedHelpers.findAndHookMethod(WebView.class, "loadUrl", String.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    // (new Exception(TAG + ".WebView.loadUrl()")).printStackTrace(); // 这里可以查看弹窗 trace
                    String url = (String) param.args[0];
                    if (url != null && url.contains("poplayer")) {
                        Log.d(TAG, "afterHookedMethod: WebView.loadUrl() poplayerUrl=" + param.args[0]);
                        return new Object();
                    }
                    return XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "hookPopLayer: ", e);
        }
    }

    /**
     * hook 后台的更新 thread， 禁止弹出更新对话框
     */
    private void hookUpdateThread() {
        try {
            XposedHelpers.findAndHookMethod(Application.class, "onCreate", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    String applicationName = param.thisObject.getClass().getName();
                    Log.d(TAG, "after hook Application.onCreate(): applicationName=" + applicationName);
                    if (TextUtils.equals(applicationName, "c8.ygp")) {
                        Log.d(TAG, "afterHookedMethod: hook com.taobao.tao.update.UpdateApplication.onCreate()");

                        String dexPath = DATA_PATH + "/lib/libcom_taobao_tao_update.so";
                        DexClassLoader loader = new DexClassLoader(dexPath, DATA_PATH, null, TaobaoHook.this.getClassLoader());
                        Class cls = loader.loadClass("c8.Nyq");
                        XposedHelpers.findAndHookMethod(cls, "execute", Runnable.class, new XC_MethodReplacement() {

                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                Log.d(TAG, "replaceHookedMethod: noop update thread");
                                (new Exception()).printStackTrace();
                                return new Object();
                            }
                        });
                    }

                }
            });
        } catch (Exception e) {
            Log.e(TAG, "hookUpdateThread: ", e);
        }
    }

    /**
     * hook 更新对话框
     *
     * @throws Throwable
     * @see TaobaoHook#hookUpdateThread()
     */
    @Deprecated
    private void fakeUpdateDialog() throws Throwable {
        XposedHelpers.findAndHookMethod(TaobaoHook.this.getClassLoader().loadClass("android.app.Activity"), "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                String className = param.thisObject.getClass().getSimpleName();
                if (TextUtils.equals(className, "Welcome")) {
                    Log.i(TAG, "afterHookedMethod: hook taobao Welcome");

                    XposedHelpers.findAndHookMethod(Dialog.class, "show", new XC_MethodReplacement() {

                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            (new Exception()).printStackTrace();
                            String className = param.thisObject.getClass().getSimpleName();
                            if (TextUtils.equals(className, "Fyq")) {
                                Log.i(TAG, "afterHookedMethod: nop c8.Fyq.show()");
                                return new Object();
                            }
                            return XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
                        }
                    });
                }
            }
        });
    }

    private void fakeBackUp() throws Throwable {
        // hook atlas Application
//        XposedHelpers.findAndHookMethod(this.getClassLoader().loadClass("com.ali.mobisecenhance.ld.BridgeApp2"), "onCreate", new XC_MethodHook() {
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                Log.i(TAG, "afterHookedMethod: hook BridgeApp2");
//                Log.i(TAG, "afterHookedMethod: hook BridgeApp233333333333");
//
//                XposedHelpers.findAndHookConstructor(File.class, String.class, new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        File f = (File) param.thisObject;
//                        if (f != null && f.getAbsolutePath().contains("libcom")) {
//                            Log.i(TAG, "wcy_afterHookedMethod: " + f.getAbsolutePath());
//                            (new Exception()).printStackTrace();
//                        }
//
//                    }
//                });
//            }
//        });

        // 以下是 淘宝 atlas 框架动态加载 apk 的 一些 hook 尝试
//        XposedHelpers.findAndHookMethod(TaobaoHook.this.getClassLoader().loadClass("c8.Cs"), "installNewBundle", String.class, File.class, new BaggioFaker());

//        TaobaoHook.this.hookMethods("c8.Cs", "getInstalledBundle", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                Log.i(TAG, "afterHookedMethod: getInstalledBundle");
//            }
//        });

        // BundleInstaller
//        TaobaoHook.this.hookMethods("c8.zs", "installBundleFromApk", new BaggioFaker());
//        TaobaoHook.this.hookMethods("c8.Cs", "installNewBundle", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                Log.i(TAG, "afterHookedMethod: installNewBundle");
//            }
//        });
//        TaobaoHook.this.hookMethods("c8.qs", "installBundle", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                Log.i(TAG, "afterHookedMethod: installBundle");
//            }
//        });
//
//        XposedHelpers.findAndHookMethod(TaobaoHook.this.getClassLoader().loadClass("c8.qs"), "installBundleTransitivelyAsync",
//                String[].class, TaobaoHook.this.getClassLoader().loadClass("c8.ys"), new BaggioFaker());
//
//        TaobaoHook.this.hookMethods("c8.qs", "installDelayBundleTransitively", new BaggioFaker());
//        TaobaoHook.this.hookMethods("c8.qs", "installIdleBundleTransitively", new BaggioFaker());
    }
}
