package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.hook.faker.FileExistsFaker;

import java.io.File;
import java.util.regex.Pattern;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:51
 * desc  :
 * update: Shawn 2018/6/22 17:51
 */
public class GenymotionHook extends AbstractHook {
    private static final String TAG = "GenymotionHook";

    public GenymotionHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeGenyFile();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeGenyFile() {
        try {
            XposedHelpers.findAndHookMethod(File.class, "exists", new FileExistsFaker() {

                @Override
                protected MethodHookParam fakeParams(MethodHookParam param) {
                    File file = (File) param.thisObject;
                    String absolutePath = file.getAbsolutePath();
                    String regex = "/dev/socket/genyd|/dev/socket/baseband_genyd";
                    if (Pattern.matches(regex, absolutePath)) {
                        param.setResult(false);
                    }
                    return param;
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "fakeGenyFile: ", e);
        }
    }
}
