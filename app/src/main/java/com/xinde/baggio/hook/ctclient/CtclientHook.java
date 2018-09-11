package com.xinde.baggio.hook.ctclient;

import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.hook.AbstractHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class CtclientHook extends AbstractHook {

    private static final String TAG = "CtclientHook";

    public CtclientHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        Log.i(TAG, "handleLoadPackage: start...");
        if (!TextUtils.equals(this.loadPackageParam.packageName, "com.ct.client")) return;

        try {
            Log.i(TAG, "handleLoadPackage: ct client...");

            // 1. hook Application.onCreate()
            XposedHelpers.findAndHookMethod("android.app.Application", this.getClassLoader(), "onCreate", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.i(TAG, "afterHookedMethod: hooked Application.onCreate()");

                    // 2. hook log switch and modify it
                    Class<?> clazz = CtclientHook.this.getClassLoader().loadClass("com.ct.client.common.d");
                    XposedHelpers.findField(clazz, "a").set(null, true);
                    Log.i(TAG, "afterHookedMethod: hook log succeed");
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "handleLoadPackage: " + e.getMessage());
        }
    }
}
