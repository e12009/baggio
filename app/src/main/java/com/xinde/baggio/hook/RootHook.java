package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.hook.faker.FileExistsFaker;

import java.io.File;
import java.util.regex.Pattern;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/6 19:09
 * desc  :
 * update: Shawn 2018/7/6 19:09
 */
public class RootHook extends AbstractHook {
    private static final String TAG = "RootHook";

    public RootHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeRootFile();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeRootFile() {
        try {
            XposedHelpers.findAndHookMethod(File.class, "exists", new FileExistsFaker() {

                @Override
                protected MethodHookParam fakeParams(MethodHookParam param) {
                    File file = (File) param.thisObject;
                    String absolutePath = file.getAbsolutePath();
                    String regex = "/sbin/su|/system/bin/su|/system/xbin/su";
                    if (Pattern.matches(regex, absolutePath)) {
                        param.setResult(false);
                    }
                    return param;
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);


                }
            });
        } catch (Exception e) {
            Log.e(TAG, "fakeRootFile: ", e);
        }

    }
}
