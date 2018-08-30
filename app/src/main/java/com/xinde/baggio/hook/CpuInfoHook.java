package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.hook.faker.RedirectUtil;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:49
 * desc  :
 * update: Shawn 2018/6/22 17:49
 */
public class CpuInfoHook extends AbstractHook {

    private static final String TAG = "CpuInfoHook";

    public CpuInfoHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeCpuInfo();
        fakeCpuRuntime();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeCpuInfo() {
        String oriFilePath = Constant.CPUINFO_PATH;
        String destFilePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.CPUINFO_FILE_NAME);
        try {
            RedirectUtil.redirectFromFile(oriFilePath, destFilePath);
            RedirectUtil.redirectFromInputStream(oriFilePath, destFilePath);
            RedirectUtil.redirectFromRuntime(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromPattern(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromProcessBuilder(oriFilePath, destFilePath);
        } catch (Exception e) {
            Log.e(TAG, "fakeCpuInfo: ", e);
        }
    }

    private void fakeCpuRuntime() {
        try {
            // fake cpu dir
            String oriCpuPath = "/sys/devices/system/cpu";
            String destCpuPath = Constant.FAKE_PATH + "/" + "cpu";
            RedirectUtil.redirectFromFile(oriCpuPath, destCpuPath);
            RedirectUtil.redirectFromInputStream(oriCpuPath, destCpuPath);

            // fake cpuinfo_max_freq
            String oriMaxPath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
            String destMaxPath = Constant.FAKE_PATH + "/" + "cpu/cpu0/cpufreq/cpuinfo_max_freq";
            RedirectUtil.redirectFromFile(oriMaxPath, destMaxPath);
            RedirectUtil.redirectFromInputStream(oriMaxPath, destMaxPath);
        } catch (Exception e) {
            Log.e(TAG, "fakeCpuRuntime: ", e);
        }
    }
}
