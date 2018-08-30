package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.SPKey.WifiInfo;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.hook.faker.PenetrationObject;
import com.xinde.baggio.util.SharedPref;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/9 13:42
 * desc  :
 * update: Shawn 2018/7/9 13:42
 */
public class NetworkInfoHook extends AbstractHook {
    private static final String TAG = "NetworkInfoHook";

    public NetworkInfoHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeNetWorkType();
        fakeWifiInfo();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeNetWorkType() {
        try {
            hookMethod("android.net.NetworkInfo", "getType", new BaggioFaker(new PenetrationObject()));
        } catch (Exception e) {
            Log.e(TAG, "fakeNetWorkType: ", e);
        }
    }

    private void fakeWifiInfo() {
        try {
            // mac address
            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getMacAddress", new BaggioFaker(SharedPref.getXValue(WifiInfo.MAC_ADDRESS)));

            // 内网 IP address
            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getIpAddress", new BaggioFaker(Integer.parseInt(SharedPref.getXValue(WifiInfo.IP_ADDRESS))));

            // wifi 强度
            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getBSSID", new BaggioFaker(SharedPref.getXValue(WifiInfo.BSSID)));

            // wifi 名称
            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getSSID", new BaggioFaker(SharedPref.getXValue(WifiInfo.SSID)));

            // todo test 以下
            XposedHelpers.findAndHookMethod("android.net.wifi.WifiManager", this.getClassLoader(),
                    "getScanResults", new BaggioFaker());

            XposedHelpers.findAndHookMethod("android.net.wifi.WifiManager", this.getClassLoader(),
                    "isWifiEnabled", new BaggioFaker());

            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", this.getClassLoader(),
                    "isConnectedOrConnecting", new BaggioFaker());

            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", this.getClassLoader(),
                    "isConnected", new BaggioFaker());

            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", this.getClassLoader(),
                    "isAvailable", new BaggioFaker());

            XposedHelpers.findAndHookMethod("android.telephony.CellInfo", this.getClassLoader(),
                    "isRegistered", new BaggioFaker());

        } catch (Exception e) {
            Log.e(TAG, "fakeWifiInfo: ", e);
        }
    }
}
