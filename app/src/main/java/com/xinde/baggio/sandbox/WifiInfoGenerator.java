package com.xinde.baggio.sandbox;

import android.content.Context;
import android.util.Log;

import com.xinde.baggio.SPKey.WifiInfo;
import com.xinde.baggio.util.SharedPref;

/**
 * author: Shawn
 * time  : 2018/7/9 13:46
 * desc  :
 * update: Shawn 2018/7/9 13:46
 */
public class WifiInfoGenerator extends AbstractGenerator {
    private static final String TAG = "WifiInfoGenerator";

    public WifiInfoGenerator(Context context) {
        super(context);
    }

    @Override
    public void run() {
        setWifiInfo();

        Log.i(TAG, TAG + " running...");
    }

    private void setWifiInfo() {
        SharedPref mySP = new SharedPref(this.context);

        mySP.setSharedPref(WifiInfo.SSID, "Tenda");
        mySP.setSharedPref(WifiInfo.BSSID, "0c:1d:af:58:a2:d2");
        mySP.setSharedPref(WifiInfo.MAC_ADDRESS, "50:bd:5f:0a:9c:58");
        // FIXME: 2018/7/16 ???
        mySP.setSharedPref(WifiInfo.IP_ADDRESS, "1912711360");
    }
}
