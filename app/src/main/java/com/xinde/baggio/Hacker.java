package com.xinde.baggio;

import android.text.TextUtils;
import android.util.Log;


import com.xinde.baggio.hook.douyin.DouyinHook;
import com.xinde.baggio.hook.taobao.TaobaoHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:55
 * desc  :
 * update: Shawn 2018/6/22 17:55
 */
public class Hacker implements IXposedHookLoadPackage {
    private static final String TAG = "Hacker";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.i(TAG, "handleLoadPackage: start hack...");

        if (TextUtils.equals(lpparam.packageName, "com.ss.android.ugc.aweme")) {
            Log.d(TAG, "handleLoadPackage: start hook douyin");
            new DouyinHook(lpparam).run();
        }
    }
}
