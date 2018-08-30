package com.xinde.baggio.sandbox;

import android.content.Context;
import android.util.Log;

import com.xinde.baggio.SPKey.SensorKey;
import com.xinde.baggio.util.SharedPref;

/**
 * author: Shawn
 * time  : 2018/7/11 17:13
 * desc  :
 * update: Shawn 2018/7/11 17:13
 */
public class SensorGenerator extends AbstractGenerator {
    private static final String TAG = "SensorGenerator";

    public SensorGenerator(Context context) {
        super(context);
    }

    @Override
    public void run() {
        setAllSensor();
        setAccelerometerSensor();
        setGravitySensor();
        setGyroscopeSensor();

        Log.i(TAG, TAG + " running...");
    }

    private void setAllSensor() {
        SharedPref mySP = new SharedPref(this.context);
        String content = "[\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 2500,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 0,\n" +
                "        \"mMaxRange\": 19.595264,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Accelerometer Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.000598,\n" +
                "        \"mType\": 1,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 2222,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 1,\n" +
                "        \"mMaxRange\": 1966.0801,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Magnetometer Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.060000002,\n" +
                "        \"mType\": 2,\n" +
                "        \"mVendor\": \"AKM\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1428,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 2,\n" +
                "        \"mMaxRange\": 1966.0801,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Magnetometer Uncalibrate Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.060000002,\n" +
                "        \"mType\": 14,\n" +
                "        \"mVendor\": \"AKM\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 2500,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 6,\n" +
                "        \"mMaxRange\": 40.009727,\n" +
                "        \"mMinDelay\": 5000,\n" +
                "        \"mName\": \"Gyroscope Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.001221,\n" +
                "        \"mType\": 4,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1428,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 7,\n" +
                "        \"mMaxRange\": 40.009727,\n" +
                "        \"mMinDelay\": 5000,\n" +
                "        \"mName\": \"Gyroscope Uncalibrate Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.001221,\n" +
                "        \"mType\": 16,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 0,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 14,\n" +
                "        \"mMaxRange\": 1.0,\n" +
                "        \"mMinDelay\": -1,\n" +
                "        \"mName\": \"Significant Motion Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 1.0,\n" +
                "        \"mType\": 17,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1052,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 10,\n" +
                "        \"mMaxRange\": 1.0,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Rotation Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 5.9604645E-8,\n" +
                "        \"mType\": 11,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1052,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 11,\n" +
                "        \"mMaxRange\": 1.0,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Game Rotation Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 5.9604645E-8,\n" +
                "        \"mType\": 15,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 4000,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 15,\n" +
                "        \"mMaxRange\": 1.0,\n" +
                "        \"mMinDelay\": 0,\n" +
                "        \"mName\": \"Step Detector Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 1.0,\n" +
                "        \"mType\": 18,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1333,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 3,\n" +
                "        \"mMaxRange\": 360.0,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Orientation Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.000021457672,\n" +
                "        \"mType\": 3,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1428,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 9,\n" +
                "        \"mMaxRange\": 19.595264,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Linear Acceleration Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.000598,\n" +
                "        \"mType\": 10,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1428,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 8,\n" +
                "        \"mMaxRange\": 19.595264,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"Gravity Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 0.000598,\n" +
                "        \"mType\": 9,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 2857,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 16,\n" +
                "        \"mMaxRange\": 65535.0,\n" +
                "        \"mMinDelay\": 0,\n" +
                "        \"mName\": \"Step Counter Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 1.0,\n" +
                "        \"mType\": 19,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 1052,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 17,\n" +
                "        \"mMaxRange\": 1.0,\n" +
                "        \"mMinDelay\": 10000,\n" +
                "        \"mName\": \"GeoMagnetic Rotation Sensor\",\n" +
                "        \"mPower\": 1.0,\n" +
                "        \"mResolution\": 5.9604645E-8,\n" +
                "        \"mType\": 20,\n" +
                "        \"mVendor\": \"STMicroelectronics\",\n" +
                "        \"mVersion\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"mFifoMaxEventCount\": 0,\n" +
                "        \"mFifoReservedEventCount\": 0,\n" +
                "        \"mHandle\": 4,\n" +
                "        \"mMaxRange\": 30000.0,\n" +
                "        \"mMinDelay\": 0,\n" +
                "        \"mName\": \"Ambient Light Sensor\",\n" +
                "        \"mPower\": 0.1,\n" +
                "        \"mResolution\": 5.9604643E-10,\n" +
                "        \"mType\": 5,\n" +
                "        \"mVendor\": \"Intersil\",\n" +
                "        \"mVersion\": 1\n" +
                "    }\n" +
                "]";
        mySP.setSharedPref(SensorKey.TYPE_ALL, content);
    }


    private void setAccelerometerSensor() {
        SharedPref mySP = new SharedPref(this.context);
        String content = "[{\n" +
                "    \"mFifoMaxEventCount\": 2500,\n" +
                "    \"mFifoReservedEventCount\": 0,\n" +
                "    \"mHandle\": 0,\n" +
                "    \"mMaxRange\": 19.595264,\n" +
                "    \"mMinDelay\": 10000,\n" +
                "    \"mName\": \"Accelerometer Sensor\",\n" +
                "    \"mPower\": 1.0,\n" +
                "    \"mResolution\": 0.000598,\n" +
                "    \"mType\": 1,\n" +
                "    \"mVendor\": \"STMicroelectronics\",\n" +
                "    \"mVersion\": 1\n" +
                "}]";
        mySP.setSharedPref(SensorKey.TYPE_ACCELEROMETER, content);
    }

    private void setGyroscopeSensor() {
        SharedPref mySP = new SharedPref(this.context);
        String content = "[{\n" +
                "    \"mFifoMaxEventCount\": 2500,\n" +
                "    \"mFifoReservedEventCount\": 0,\n" +
                "    \"mHandle\": 6,\n" +
                "    \"mMaxRange\": 40.009727,\n" +
                "    \"mMinDelay\": 5000,\n" +
                "    \"mName\": \"Gyroscope Sensor\",\n" +
                "    \"mPower\": 1.0,\n" +
                "    \"mResolution\": 0.001221,\n" +
                "    \"mType\": 4,\n" +
                "    \"mVendor\": \"STMicroelectronics\",\n" +
                "    \"mVersion\": 1\n" +
                "}]";
        mySP.setSharedPref(SensorKey.TYPE_GYROSCOPE, content);
    }


    private void setGravitySensor() {
        SharedPref mySP = new SharedPref(this.context);
        String content = "[{\n" +
                "    \"mFifoMaxEventCount\": 1428,\n" +
                "    \"mFifoReservedEventCount\": 0,\n" +
                "    \"mHandle\": 8,\n" +
                "    \"mMaxRange\": 19.595264,\n" +
                "    \"mMinDelay\": 10000,\n" +
                "    \"mName\": \"Gravity Sensor\",\n" +
                "    \"mPower\": 1.0,\n" +
                "    \"mResolution\": 0.000598,\n" +
                "    \"mType\": 9,\n" +
                "    \"mVendor\": \"STMicroelectronics\",\n" +
                "    \"mVersion\": 1\n" +
                "}]";
        mySP.setSharedPref(SensorKey.TYPE_GRAVITY, content);
    }
}
