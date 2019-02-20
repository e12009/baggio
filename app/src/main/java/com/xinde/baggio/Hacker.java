package com.xinde.baggio;

import android.text.TextUtils;
import android.util.Log;


import com.xinde.baggio.hook.douyin.DouyinHook;
import com.xinde.baggio.hook.taobao.TaobaoHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:55
 * desc  :
 * update: Shawn 2018/6/22 17:55
 */
public class Hacker implements IXposedHookLoadPackage {
    private static final String TAG = "Hacker";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.i(TAG, "handleLoadPackage: start hack...");

        if (TextUtils.equals(lpparam.packageName, "com.eg.android.AlipayGphone")
                || TextUtils.equals(lpparam.packageName, "com.xinde.baresi")
                || TextUtils.equals(lpparam.packageName, "com.taobao.taobao")
                || TextUtils.equals(lpparam.packageName, "com.ss.android.ugc.aweme")
                ) {
//            new BuildInfoHook(lpparam).run();
//            new CpuInfoHook(lpparam).run();
//            new GenymotionHook(lpparam).run();
//            new QemuHook(lpparam).run();
//            new DriverHook(lpparam).run();
//            new RootHook(lpparam).run();
//            new NetworkInfoHook(lpparam).run();
//            new TelephonyHook(lpparam).run();
//            new BluetoothHook(lpparam).run();
//            new OpenGLHook(lpparam).run(); // Alipay 未调用
//            new DisplayHook(lpparam).run();
//            new LocationHook(lpparam).run();
//            new PackageManagerHook(lpparam).run();
//            new RuntimeHook(lpparam).run();
//            new BatteryHook(lpparam).run();
//            new SensorManagerHook(lpparam).run();

//            new JavaChecker(lpparam).run();

            // hack alipay
//            new AlipayHook(lpparam).run();

            // hack taobao
//            new TaobaoHook(lpparam).run();
            new DouyinHook(lpparam).run();
        }
    }
}
