package com.xinde.baggio.hook.douyin;

import android.os.Build;
import android.util.Log;

import com.xinde.baggio.SPKey;
import com.xinde.baggio.hook.AbstractHook;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.util.SharedPref;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
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
        Log.d(TAG, "run: ");
        hookEncrypt();
        hookSearch();
    }

    private void hookSearch() {
        try {
            hookMethods(" com.ss.android.ugc.aweme.discover.api.SearchApi", "a", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d(TAG, "afterHookedMethod: " + param);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "hookSearch: running");
    }

    private void hookEncrypt() {
        try {
            hookMethod("com.bytedance.frameworks.core.encrypt.a", "a", String.class, List.class, new BaggioFaker());
//            Class<?> cls = this.getClassLoader().loadClass("com.bytedance.frameworks.core.encrypt.a");
//            XposedHelpers.findAndHookMethod(cls, "a", String.class, List.class, new BaggioFaker());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "hookEncrypt: running");
    }

    private void hookLog() {
        try {
            Class<?> cls = this.getClassLoader().loadClass("com.bytedance.utils.LogUtils");
            XposedHelpers.findField(cls, "sIsLoggable").set(null, true);
            Log.d(TAG, "run: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
