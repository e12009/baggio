package com.xinde.baggio.hook.taobao;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.hook.AbstractHook;

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

    public TaobaoHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {

        // TODO: 2018/8/16 解嵌套
        try {
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
//            XposedHelpers.findAndHookMethod(this.getClassLoader().loadClass("com.ali.mobisecenhance.ld.BridgeApp2"), "onCreate", new XC_MethodHook() {
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//
//                    Log.i(TAG, "afterHookedMethod: hook BridgeApp2");
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
