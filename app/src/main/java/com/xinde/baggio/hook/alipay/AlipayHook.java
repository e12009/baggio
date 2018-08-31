package com.xinde.baggio.hook.alipay;

import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.hook.AbstractHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/31 11:51
 * desc  :
 * update: Shawn 2018/7/31 11:51
 */
public class AlipayHook extends AbstractHook {
    private static final String TAG = "AlipayHook";

    public AlipayHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        Log.i(TAG, "run: start hook alipay...");

        try {
//            back();
            XposedHelpers.findAndHookMethod("android.app.Application", this.getClassLoader(), "onCreate", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedHelpers.findAndHookMethod("android.app.Activity", AlipayHook.this.getClassLoader(), "onCreate", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            Object thisObject = param.thisObject;
                            if (!TextUtils.equals(thisObject.getClass().getSimpleName(), "RecommandAlipayUserLoginActivity"))
                                return;

                            String className = "com.ali.user.mobile.service.impl.UserLoginServiceImpl";
                            hookMethod(className, "unifyLoginPb", new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    Log.i(TAG, "afterHookedMethod: " + param.args.toString());
                                }
                            });

                        }
                    });
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "handleLoadPackage: " + e.getMessage());
        }
    }

    private void back() {
        // 1. hook Application.onCreate()
        XposedHelpers.findAndHookMethod("android.app.Application", this.loadPackageParam.classLoader, "onCreate", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.i(TAG, "afterHookedMethod: hooked Application.onCreate()");

//                    MessageDigest.getInstance("").digest()

//                    hookDeviceInfo();
                String className = "com.ali.user.mobile.service.impl.UserLoginServiceImpl";
                hookMethod(className, "unifyLoginPb", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Log.i(TAG, "afterHookedMethod: " + param.args.toString());
                    }
                });
            }


        });
    }


    private void hookDeviceInfo() {
        String className = "com.alipay.security.mobile.module.deviceinfo.b";
        // 2. hook log switch and modify it
//                    Class<?> clazz = AlipayHook.this.loadPackageParam.classLoader.loadClass("com.alipay.security.mobile.module.deviceinfo.b");
//                    XposedHelpers.findField(clazz, "a").set(null, true);
        hookMethods(className, "a", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                String ss = (String) param.getResult();
                Log.i(TAG, "afterHookedMethod: ss -> " + ss);
            }
        });
    }
}
