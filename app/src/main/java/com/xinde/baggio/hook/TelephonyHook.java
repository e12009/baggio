package com.xinde.baggio.hook;

import android.os.Build;
import android.util.Log;

import com.xinde.baggio.SPKey.Telephony;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.util.SharedPref;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/9 14:29
 * desc  :
 * update: Shawn 2018/7/9 14:29
 */
public class TelephonyHook extends AbstractHook {
    private static final String TAG = "TelephonyHook";

    public TelephonyHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeIMEI();
        fakeTelephony();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeIMEI() {
        try {
            XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneSubInfo", this.getClassLoader(), "getDeviceId", new BaggioFaker(SharedPref.getXValue(Telephony.DEVICE_ID)));

            if (Build.VERSION.SDK_INT < 22) {
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.gsm.GSMPhone", this.getClassLoader(), "getDeviceId", new BaggioFaker(SharedPref.getXValue(Telephony.DEVICE_ID)));
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneProxy", this.getClassLoader(), "getDeviceId", new BaggioFaker(SharedPref.getXValue(Telephony.DEVICE_ID)));
            }
        } catch (Exception e) {
            Log.e(TAG, "fakeIMEI: ", e);
        }
    }

    private void fakeTelephony() {
        String className = "android.telephony.TelephonyManager";
        try {
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getDeviceId", new BaggioFaker(SharedPref.getXValue(Telephony.DEVICE_ID)));
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getLine1Number", new BaggioFaker(SharedPref.getXValue(Telephony.LINE1_NUMBER)));
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getNetworkOperatorName", new BaggioFaker(SharedPref.getXValue(Telephony.NETWORK_OPERATOR_NAME)));
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getNetworkType", new BaggioFaker(SharedPref.getXValue(Telephony.NETWORK_TYPE)));
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getSimSerialNumber", new BaggioFaker(SharedPref.getXValue(Telephony.SIM_SERIAL_NUMBER)));
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getSimOperatorName", new BaggioFaker(SharedPref.getXValue(Telephony.SIM_OPERATOR_NAME)));
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getSubscriberId", new BaggioFaker(SharedPref.getXValue(Telephony.SUBSCRIBER_ID)));

            // 以下 Alipay 未调用
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getNetworkOperator", new BaggioFaker(SharedPref.getXValue(Telephony.NETWORK_OPERATOR_NAME)));
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getDeviceSoftwareVersion", new BaggioFaker());
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getNetworkCountryIso", new BaggioFaker());
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getSimCountryIso", new BaggioFaker());
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getPhoneType", new BaggioFaker());
//        XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getSimState", new BaggioFaker());
        } catch (Exception e) {
            Log.e(TAG, "fakeTelephony: ", e);
        }
    }
}
