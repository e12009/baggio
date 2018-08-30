package com.xinde.baggio.hook.faker;

import android.util.Log;

import com.xinde.baggio.Constant;

import de.robv.android.xposed.XC_MethodHook;

/**
 * author: Shawn
 * time  : 2018/7/12 18:48
 * desc  :
 * update: Shawn 2018/7/12 18:48
 */
public class InputStreamRedirectFaker extends XC_MethodHook {
    private static final String TAG = "InputStreamRedirectFake";

    private String oriFilePath;
    private String destFilePath;

    public InputStreamRedirectFaker(String oriFilePath, String destFilePath) {
        this.oriFilePath = oriFilePath;
        this.destFilePath = destFilePath;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (param.args.length == 1 && (param.args[0] instanceof String)) {
            if (param.args[0].equals(this.oriFilePath)) {
                param.args[0] = this.destFilePath;

                if (Constant.CheckSwitch) {
                    String format = String.format("redirect FileInputStream: %s -> %s", this.oriFilePath, this.destFilePath);
                    Log.i(TAG, "redirect File: " + format);
                }
            }
        }
    }
}
