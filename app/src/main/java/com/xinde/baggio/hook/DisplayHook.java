package com.xinde.baggio.hook;

import android.util.DisplayMetrics;
import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:53
 * desc  :
 * update: Shawn 2018/6/22 17:53
 */
public class DisplayHook extends AbstractHook {
    private static final String TAG = "DisplayHook";

    public DisplayHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeDisplay();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeDisplay() {
        String className = "android.view.Display";
        try {
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getMetrics", DisplayMetrics.class, new BaggioFaker());
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getRealMetrics", DisplayMetrics.class, new BaggioFaker());
        } catch (Exception e) {
            Log.e(TAG, "fakeDisplay: ", e);
        }
    }
}
