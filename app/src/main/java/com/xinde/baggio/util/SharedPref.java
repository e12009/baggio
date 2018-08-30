package com.xinde.baggio.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.xinde.baggio.Constant;

import de.robv.android.xposed.XSharedPreferences;

public class SharedPref {
    private static final String TAG = "SharedPref";
    private SharedPreferences mySharedPref;
    private static XSharedPreferences myXsharedPref;

    public SharedPref(Context appContext) {
        mySharedPref = appContext.getSharedPreferences(Constant.FAKER_FILE_NAME, Context.MODE_WORLD_READABLE);
    }

    public void setSharedPref(String key, String value) {
        try {
            mySharedPref.edit().putString(key, value).apply();
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
    }

    public void setIntSharedPref(String key, int value) {
        try {
            mySharedPref.edit().putInt(key, value).apply();
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
    }

    public void setLongSharedPref(String key, long value) {
        try {
            mySharedPref.edit().putLong(key, value).apply();
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
    }

    public void setFloatSharedPref(String key, float value) {
        try {
            mySharedPref.edit().putFloat(key, value).apply();
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
    }

    public static XSharedPreferences getMyXSharedPref() {
        if (myXsharedPref != null) {
            myXsharedPref.reload();
            return myXsharedPref;
        }
        myXsharedPref = new XSharedPreferences(Constant.PACKAGE_NAME, Constant.FAKER_FILE_NAME);
        return myXsharedPref;
    }

    public static String getXValue(String key) {
        String value = "";
        try {
            value = getMyXSharedPref().getString(key, null);
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
        return value;
    }

    public static int getIntXValue(String key) {
        int value = 0;
        try {
            value = getMyXSharedPref().getInt(key, 0);
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
        return value;
    }

    public static float getFloatXValue(String key) {
        float value = 0;
        try {
            value = getMyXSharedPref().getFloat(key, 0);
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
        return value;
    }

    public static float getLongXValue(String key) {
        float value = 0;
        try {
            value = getMyXSharedPref().getLong(key, 0);
        } catch (Exception e) {
            Log.e(TAG, "setSharedPref: ", e);
        }
        return value;
    }
}
