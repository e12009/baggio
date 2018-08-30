package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/9 14:14
 * desc  :
 * update: Shawn 2018/7/9 14:14
 */
public class BluetoothHook extends AbstractHook {
    private static final String TAG = "BluetoothHook";

    public BluetoothHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        // TODO: 2018/7/16 opti
        // 未调用
        XposedHelpers.findAndHookMethod("android.bluetooth.BluetoothAdapter", this.getClassLoader(),
                "getDefaultAdapter", new BaggioFaker(new Object())); // todo new Object() test

        // 获取 MAC 未调用
        XposedHelpers.findAndHookMethod("android.bluetooth.BluetoothAdapter", this.getClassLoader(),
                "getAddress", new BaggioFaker());

        // 获取 MAC 未调用
        XposedHelpers.findAndHookMethod("android.bluetooth.BluetoothDevice", this.getClassLoader(),
                "getAddress", new BaggioFaker());

        Log.i(TAG, TAG + " running...");
    }
}
