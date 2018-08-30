package com.xinde.baggio.hook;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.SPKey.PropKey;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.hook.faker.RedirectUtil;
import com.xinde.baggio.util.SharedPref;

import java.lang.reflect.Member;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:52
 * desc  :
 * update: Shawn 2018/6/22 17:52
 */
public class BuildInfoHook extends AbstractHook {
    private static final String TAG = "BuildInfoHook";

    public BuildInfoHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeBuildField();
        fakeBaseBand();
//        fakeAndroidSerial(this.loadPackageParam);
        fakeVersionFile();
        fakeBuildPropFile();
        fakeOthers();

        Log.i(TAG, TAG + " running...");
    }

    /**
     * todo 暂时不调用
     */
    private void fakeAndroidSerial() {
        try {
            Class<?> classBuild = XposedHelpers.findClass("android.os.Build", this.getClassLoader());
            XposedHelpers.setStaticObjectField(classBuild, "SERIAL", SharedPref.getXValue("serial")); // 串口序列号


            // TODO: 2018/6/25 未测试
            @SuppressLint("PrivateApi") Class<?> classSysProp = Class.forName("android.os.SystemProperties");
            XposedHelpers.findAndHookMethod(classSysProp, "get", String.class, new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    String key = (String) param.args[0];
                    Log.i(TAG, "afterHookedMethod: " + key);
                    switch (key) {
                        case "gsm.version.baseband":
                            param.setResult(SharedPref.getXValue(PropKey.BASE_BAND));
                            break;
                        case "ro.kernel.qemu":
                            param.setResult(0);
                            break;
                        default: // "no message"
                            break;
                    }
                }

            });
        } catch (Exception ex) {
            Log.e(TAG, "fakeAndroidSerial: ", ex);
        }
    }


    private void fakeBaseBand() {
        try {
            // 固件版本
            XposedHelpers.findAndHookMethod("android.os.Build", this.getClassLoader(),
                    "getRadioVersion", new BaggioFaker(SharedPref.getXValue(PropKey.BASE_BAND)));
        } catch (Exception e) {
            Log.e(TAG, "fakeBaseBand: ", e);
        }
    }


    private void fakeBuildField() {
        try {
            XposedHelpers.findField(Build.class, "BRAND").set(null, SharedPref.getXValue(PropKey.BRAND));
            XposedHelpers.findField(Build.class, "BOARD").set(null, SharedPref.getXValue(PropKey.BOARD));
            XposedHelpers.findField(Build.class, "CPU_ABI").set(null, SharedPref.getXValue(PropKey.CPU_ABI));
            XposedHelpers.findField(Build.class, "CPU_ABI2").set(null, SharedPref.getXValue(PropKey.CPU_ABI2));
            XposedHelpers.findField(Build.class, "DEVICE").set(null, SharedPref.getXValue(PropKey.DEVICE));
            XposedHelpers.findField(Build.class, "DISPLAY").set(null, SharedPref.getXValue(PropKey.DISPLAY));
            XposedHelpers.findField(Build.class, "FINGERPRINT").set(null, SharedPref.getXValue(PropKey.FINGERPRINT));
            XposedHelpers.findField(Build.class, "HARDWARE").set(null, SharedPref.getXValue(PropKey.HARDWARE));
            XposedHelpers.findField(Build.class, "ID").set(null, SharedPref.getXValue(PropKey.ID));
            XposedHelpers.findField(Build.class, "MANUFACTURER").set(null, SharedPref.getXValue(PropKey.MANUFACTURER));
            XposedHelpers.findField(Build.class, "MODEL").set(null, SharedPref.getXValue(PropKey.MODEL));
            XposedHelpers.findField(Build.class, "PRODUCT").set(null, SharedPref.getXValue(PropKey.PRODUCT));
            XposedHelpers.findField(Build.class, "BOOTLOADER").set(null, SharedPref.getXValue(PropKey.BOOTLOADER));
            XposedHelpers.findField(Build.class, "HOST").set(null, SharedPref.getXValue(PropKey.HOST));
            XposedHelpers.findField(Build.class, "TAGS").set(null, SharedPref.getXValue(PropKey.TAGS));
            XposedHelpers.findField(Build.class, "TYPE").set(null, SharedPref.getXValue(PropKey.TYPE));
            XposedHelpers.findField(Build.class, "USER").set(null, SharedPref.getXValue(PropKey.USER));
//            XposedHelpers.findField(Build.class, "NAME").set(null, SharedPref.getXValue(PropKey.NAME));
//            XposedHelpers.findField(Build.class, "TIME").set(null, SharedPref.getLongXValue(PropKey.TIME)); // hook 时找不到该属性
            XposedHelpers.findField(Build.class, "RADIO").set(null, SharedPref.getXValue(PropKey.RADIO));
            XposedHelpers.findField(Build.class, "SERIAL").set(null, SharedPref.getXValue(PropKey.SERIAL));

            XposedHelpers.findField(Build.VERSION.class, "INCREMENTAL").set(null, SharedPref.getXValue(PropKey.INCREMENTAL));
            XposedHelpers.findField(Build.VERSION.class, "RELEASE").set(null, SharedPref.getXValue(PropKey.RELEASE));
            XposedHelpers.findField(Build.VERSION.class, "SDK").set(null, SharedPref.getXValue(PropKey.SDK));
            XposedHelpers.findField(Build.VERSION.class, "SDK_INT").set(null, SharedPref.getIntXValue(PropKey.SDK_INT));
            XposedHelpers.findField(Build.VERSION.class, "CODENAME").set(null, SharedPref.getXValue(PropKey.CODENAME));
        } catch (Exception e) {
            Log.e(TAG, "fakeBuildField: ", e);
        }
    }

    private void fakeOthers() {
        // hook android_id
        try {
            XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", this.getClassLoader(),
                    "getString", ContentResolver.class, String.class, new BaggioFaker() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);

                            if (param.args[1].equals(Settings.Secure.ANDROID_ID)) {
                                param.setResult(SharedPref.getXValue(PropKey.ANDROID_ID));
                            }
                        }
                    });
        } catch (Exception ex) {
            Log.e(TAG, "fakeOthers: ", ex);
        }

        try {
            @SuppressLint("PrivateApi") Class<?> cls = Class.forName("android.os.SystemProperties");
            if (cls != null) {
                for (Member mem : cls.getDeclaredMethods()) {
                    XposedBridge.hookMethod(mem, new BaggioFaker() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);

                            // 用户的 KEY
                            if (param.args.length > 0 && param.args[0] != null && param.args[0].equals("ro.build.description")) {
                                param.setResult(SharedPref.getXValue(PropKey.DESCRIPTION));
                            }
                        }
                    });
                }
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "fakeOthers: ", e);
        }
    }


    /**
     * hook File.class, redirect version file
     */
    private void fakeVersionFile() {
        String oriFilePath = Constant.BUILD_VERSION_PATH;
        String destFilePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.VERSION_FILE_NAME);
        try {
            RedirectUtil.redirectFromFile(oriFilePath, destFilePath);
            RedirectUtil.redirectFromInputStream(oriFilePath, destFilePath);
            RedirectUtil.redirectFromRuntime(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromPattern(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromProcessBuilder(oriFilePath, destFilePath);
        } catch (Exception e) {
            XposedBridge.log("fakeVersionFile: " + e.getMessage());
        }
    }

    /**
     * hook File.class, redirect build.prop file
     */
    private void fakeBuildPropFile() {
        String oriFilePath = Constant.BUILD_PROP_PATH;
        String destFilePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.BUILD_PROP_NAME);
        try {
            RedirectUtil.redirectFromFile(oriFilePath, destFilePath);
            RedirectUtil.redirectFromInputStream(oriFilePath, destFilePath);
            RedirectUtil.redirectFromRuntime(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromPattern(this.getClassLoader(), oriFilePath, destFilePath);
            RedirectUtil.redirectFromProcessBuilder(oriFilePath, destFilePath);
        } catch (Exception e) {
            XposedBridge.log("fakeVersionFile: " + e.getMessage());
        }
    }
}
