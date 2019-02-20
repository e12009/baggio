package com.xinde.baggio.hook.douyin;

import android.os.Build;
import android.util.Log;

import com.xinde.baggio.SPKey;
import com.xinde.baggio.hook.AbstractHook;
import com.xinde.baggio.util.SharedPref;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 1/14/19 3:41 PM
 * desc  :
 * update: Shawn 1/14/19 3:41 PM
 */
public class DouyinHook extends AbstractHook {
    private static final String TAG = "DouyinHook";

    public DouyinHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);

    }

    @Override
    public void run() {
        try {
            Class<?> cls = this.getClassLoader().loadClass("com.bytedance.utils.LogUtils");
            XposedHelpers.findField(cls, "sIsLoggable").set(null, true);
            Log.d(TAG, "run: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        XposedHelpers.setBooleanField();
    }
}
