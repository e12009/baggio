package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.hook.faker.RedirectUtil;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/6 18:34
 * desc  :
 * update: Shawn 2018/7/6 18:34
 */
public class DriverHook extends AbstractHook {
    private static final String TAG = "DriverHook";

    public DriverHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeDriversInfo();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeDriversInfo() {
        String oriFilePath = Constant.DRIVERS_FILE_PATH;
        String destFilePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.DRIVERS_FILE_NAME);
        try {
            RedirectUtil.redirectFromFile(oriFilePath, destFilePath);
            RedirectUtil.redirectFromInputStream(oriFilePath, destFilePath);
            RedirectUtil.redirectFromRuntime(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromPattern(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromProcessBuilder(oriFilePath, destFilePath);
        } catch (Exception e) {
            Log.e(TAG, "fakeDriversInfo: ", e);
        }
    }
}
