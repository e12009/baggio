package com.xinde.baggio.hook;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/11 16:13
 * desc  :
 * update: Shawn 2018/7/11 16:13
 */
public class BatteryHook extends AbstractHook {
    private static final String TAG = "BatteryHook";

    public BatteryHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        XposedHelpers.findAndHookMethod(Intent.class, "getIntExtra", String.class, int.class, new BaggioFaker() {
            @Override
            protected MethodHookParam fakeParam(MethodHookParam param) {
                Intent intent = (Intent) param.thisObject;
                if (!TextUtils.equals(intent.getAction(), "android.intent.action.BATTERY_CHANGED")) return param;

                String key = (String) param.args[0];
                // TODO: 2018/7/11 伪造数据
                if (TextUtils.equals(key, "status")) {
                    param.setResult(0);
                } else if (TextUtils.equals(key, "level")) {
                    param.setResult(68);
                } else if (TextUtils.equals(key, "scale")) {
                    param.setResult(100);
                } else if (TextUtils.equals(key, "voltage")) {
                    param.setResult(6456);
                } else if (TextUtils.equals(key, "temperature")) {
                    param.setResult(42);
                } else if (TextUtils.equals(key, "isUpgrade")) {

                }
                return param;
            }
        });

        Log.i(TAG, TAG + " running...");
    }
}
