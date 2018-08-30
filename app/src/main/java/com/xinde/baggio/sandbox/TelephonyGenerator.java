package com.xinde.baggio.sandbox;

import android.content.Context;
import android.util.Log;

import com.xinde.baggio.SPKey.Telephony;
import com.xinde.baggio.util.SharedPref;

/**
 * author: Shawn
 * time  : 2018/7/9 16:03
 * desc  :
 * update: Shawn 2018/7/9 16:03
 */
public class TelephonyGenerator extends AbstractGenerator {
    private static final String TAG = "TelephonyGenerator";

    public TelephonyGenerator(Context context) {
        super(context);
    }

    @Override
    public void run() {
        fakeTelephony();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeTelephony() {
        SharedPref mySP = new SharedPref(this.context);

        mySP.setSharedPref(Telephony.DEVICE_ID, "865371030152048");
        mySP.setSharedPref(Telephony.LINE1_NUMBER, "+8618511063344");
        mySP.setSharedPref(Telephony.NETWORK_OPERATOR_NAME, "CHN-UNICOM");
        mySP.setSharedPref(Telephony.NETWORK_TYPE, "1");
        mySP.setSharedPref(Telephony.SIM_OPERATOR_NAME, "");
        mySP.setSharedPref(Telephony.SIM_SERIAL_NUMBER, "89860117811045871200");
        mySP.setSharedPref(Telephony.SUBSCRIBER_ID, "460011061518891");
    }
}
