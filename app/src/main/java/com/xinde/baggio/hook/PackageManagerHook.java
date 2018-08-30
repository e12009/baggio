package com.xinde.baggio.hook;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import java.util.List;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/10 15:05
 * desc  :
 * update: Shawn 2018/7/10 15:05
 */
public class PackageManagerHook extends AbstractHook {
    private static final String TAG = "PackageManagerHook";

    public PackageManagerHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakePackageList();
        fakeSystemFeature();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeSystemFeature() {
        try {
            hookMethods("android.app.ApplicationPackageManager",
                    "hasSystemFeature", new BaggioFaker() {

                        @Override
                        protected MethodHookParam fakeParam(MethodHookParam param) {
                            String type = (String) param.args[0];
                            if (TextUtils.equals(type, "android.hardware.bluetooth")
                                    || TextUtils.equals(type, "android.hardware.bluetooth_le")
                                    || TextUtils.equals(type, "android.hardware.type.television")
                                    || TextUtils.equals(type, "android.hardware.touchscreen.multitouch")) {
                                param.setResult(true);
                            }
                            return param;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "fakeSystemFeature: ", e);
        }
    }


    private void fakePackageList() {
        try {
            XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", this.getClassLoader(),
                    "getInstalledApplications", int.class, new BaggioFaker()); // Alipay 未调用

            XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", this.getClassLoader(),
                    "getInstalledPackages", int.class, new BaggioFaker() {
                        @Override
                        protected MethodHookParam fakeParam(MethodHookParam param) {
                            List<?> packages = (List<?>) param.getResult();
                            for (int i = 0; i < packages.size(); i++) {
                                PackageInfo packageInfo = (PackageInfo) packages.get(i);

                                String packageName = packageInfo.packageName;
                                if (packageName == null) continue;

                                if (packageName.contains("genymotion") // 含有以上包名字段可能暴露模拟器
                                        || packageName.contains("xposed")
                                        || packageName.contains("xinde")) {
                                    packages.remove(i);
                                }
                            }
                            param.setResult(packages);
                            return param;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "fakePackageList: ", e);
        }
    }
}
