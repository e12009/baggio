package com.xinde.baggio.hook;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinde.baggio.SPKey.SensorKey;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.util.SharedPref;

import java.util.List;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/11 16:36
 * desc  :
 * update: Shawn 2018/7/11 16:36
 */
public class SensorManagerHook extends AbstractHook {
    private static final String TAG = "SensorManagerHook";

    public SensorManagerHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeSensorList();

        Log.i(TAG, TAG + " running...");
    }


    /**
     * after hack {@link SensorManagerHook#fakeSensorList()}, this method is also hacked
     *
     * @deprecated
     */
    @Deprecated
    private void fakeDefaultSensor() {
        XposedHelpers.findAndHookMethod(SensorManager.class, "getDefaultSensor", int.class, new BaggioFaker() {
            @Override
            protected MethodHookParam fakeParam(MethodHookParam param) {
                int type = (int) param.args[0];
                String key = null;
                switch (type) {
                    case Sensor.TYPE_ALL:
                        key = SensorKey.TYPE_ALL;
                        break;
                    case Sensor.TYPE_ACCELEROMETER:
                        key = SensorKey.TYPE_ACCELEROMETER;
                        break;
                    case Sensor.TYPE_GRAVITY:
                        key = SensorKey.TYPE_GRAVITY;
                        break;
                    case Sensor.TYPE_GYROSCOPE:
                        key = SensorKey.TYPE_GYROSCOPE;
                        break;
                    default:
                        break;
                }

                if (key != null) {
                    List<Sensor> sensors = new Gson().fromJson(SharedPref.getXValue(key), new TypeToken<List<Sensor>>() {
                    }.getType());
                    if (sensors.size() > 0) param.setResult(sensors.get(0));
                }
                return param;
            }
        });
    }

    private void fakeSensorList() {
//        XposedHelpers.findAndHookMethod(SensorManager.class, "getSensors", new BaggioFaker()); // Alipay 未调用
        XposedHelpers.findAndHookMethod(SensorManager.class, "getSensorList", int.class, new BaggioFaker() {
            @Override
            protected MethodHookParam fakeParam(MethodHookParam param) {
                int type = (int) param.args[0];
                String key = null;
                switch (type) {
                    case Sensor.TYPE_ALL:
                        key = SensorKey.TYPE_ALL;
                        break;
                    case Sensor.TYPE_ACCELEROMETER:
                        key = SensorKey.TYPE_ACCELEROMETER;
                        break;
                    case Sensor.TYPE_GRAVITY:
                        key = SensorKey.TYPE_GRAVITY;
                        break;
                    case Sensor.TYPE_GYROSCOPE:
                        key = SensorKey.TYPE_GYROSCOPE;
                        break;
                    default:
                        break;
                }
                if (key != null) {
                    List<Sensor> sensors = new Gson().fromJson(SharedPref.getXValue(key), new TypeToken<List<Sensor>>() {
                    }.getType());
                    param.setResult(sensors);
                }
                return param;
            }
        });
    }
}
