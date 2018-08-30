package com.xinde.baggio.hook;

import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:51
 * desc  :
 * update: Shawn 2018/6/22 17:51
 */
public class LocationHook extends AbstractHook {
    private static final String TAG = "LocationHook";

    public LocationHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeBaseStation();
        fakeGps();
        fakeLocationManager();

        Log.i(TAG, TAG + " running...");
    }


    /**
     * fake 基站信息
     */
    private void fakeBaseStation() {
        String className = "android.telephony.TelephonyManager";
        try {
            // TODO: 2018/7/10 编造假数据
            Bundle bundle = new Bundle();
            bundle.putInt("lac", 4158);
            bundle.putInt("cid", 4310906);
            bundle.putInt("psc", -1);
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(),
                    "getCellLocation", new BaggioFaker(new GsmCellLocation(bundle)));

            XposedHelpers.findAndHookMethod(className, this.getClassLoader(),
                    "getAllCellInfo", new BaggioFaker());
        } catch (Exception e) {
            Log.e(TAG, "fakeBaseStation: ", e);
        }
    }

    private void fakeGps() {
        // 纬度
        XposedHelpers.findAndHookMethod("android.location.Location", this.getClassLoader(),
                "getLatitude", new BaggioFaker());

        // 经度
        XposedHelpers.findAndHookMethod("android.location.Location", this.getClassLoader(),
                "getLongitude", new BaggioFaker());
    }

    private void fakeLocationManager() {
        String className = "android.location.LocationManager";
        try {
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getLastLocation",
                    new BaggioFaker()); // Alipay
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getLastKnownLocation",
                    String.class, new BaggioFaker());
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getProviders",
                    boolean.class, new BaggioFaker());
             XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getAllProviders",
                    new BaggioFaker());
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "getBestProvider",
                    Criteria.class, boolean.class, new BaggioFaker());
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "addGpsStatusListener",
                    GpsStatus.Listener.class, new BaggioFaker());
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(), "addNmeaListener",
                    GpsStatus.NmeaListener.class, new BaggioFaker());
            XposedHelpers.findAndHookMethod(className, this.getClassLoader(),
                    "getGpsStatus", GpsStatus.class, new BaggioFaker());

            hookMethods(className, "requestLocationUpdates", new BaggioFaker());
            hookMethods(className, "requestSingleUpdate", new BaggioFaker());
        } catch (Exception e) {
            Log.e(TAG, "fakeLocationManager: ", e);
        }


    }

    private void bak(double latitude, double longtitude) {
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastLocation", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Location l = new Location(LocationManager.GPS_PROVIDER);
                l.setLatitude(latitude);
                l.setLongitude(longtitude);
                l.setAccuracy(100f);
                l.setTime(0);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    l.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
				}*/
                param.setResult(l);
            }
        });

        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastKnownLocation", String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Location l = new Location(LocationManager.GPS_PROVIDER);
                l.setLatitude(latitude);
                l.setLongitude(longtitude);
                l.setAccuracy(100f);
                l.setTime(0);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    l.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
				}*/
                param.setResult(l);
            }
        });


        XposedBridge.hookAllMethods(LocationManager.class, "getProviders", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add("gps");
                param.setResult(arrayList);
            }
        });

        XposedHelpers.findAndHookMethod(LocationManager.class, "getBestProvider", Criteria.class, Boolean.TYPE, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult("gps");
            }
        });

        XposedHelpers.findAndHookMethod(LocationManager.class, "addGpsStatusListener", GpsStatus.Listener.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if (param.args[0] != null) {
                    XposedHelpers.callMethod(param.args[0], "onGpsStatusChanged", 1);
                    XposedHelpers.callMethod(param.args[0], "onGpsStatusChanged", 3);
                }
            }
        });

        XposedHelpers.findAndHookMethod(LocationManager.class, "addNmeaListener", GpsStatus.NmeaListener.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(false);
            }
        });

        XposedHelpers.findAndHookMethod("android.location.LocationManager", this.getClassLoader(),
                "getGpsStatus", GpsStatus.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        GpsStatus gss = (GpsStatus) param.getResult();
                        if (gss == null)
                            return;

                        Class<?> clazz = GpsStatus.class;
                        Method m = null;
                        for (Method method : clazz.getDeclaredMethods()) {
                            if (method.getName().equals("setStatus")) {
                                if (method.getParameterTypes().length > 1) {
                                    m = method;
                                    break;
                                }
                            }
                        }
                        if (m == null)
                            return;

                        //access the private setStatus function of GpsStatus
                        m.setAccessible(true);

                        //make the apps belive GPS works fine now
                        int svCount = 5;
                        int[] prns = {1, 2, 3, 4, 5};
                        float[] snrs = {0, 0, 0, 0, 0};
                        float[] elevations = {0, 0, 0, 0, 0};
                        float[] azimuths = {0, 0, 0, 0, 0};
                        int ephemerisMask = 0x1f;
                        int almanacMask = 0x1f;

                        //5 satellites are fixed
                        int usedInFixMask = 0x1f;

                        XposedHelpers.callMethod(gss, "setStatus", svCount, prns, snrs, elevations, azimuths, ephemerisMask, almanacMask, usedInFixMask);
                        param.args[0] = gss;
                        param.setResult(gss);
                        try {
                            m.invoke(gss, svCount, prns, snrs, elevations, azimuths, ephemerisMask, almanacMask, usedInFixMask);
                            param.setResult(gss);
                        } catch (Exception e) {
                            XposedBridge.log(e);
                        }
                    }
                });

        for (Method method : LocationManager.class.getDeclaredMethods()) {
            if (method.getName().equals("requestLocationUpdates")
                    && !Modifier.isAbstract(method.getModifiers())
                    && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        if (param.args.length >= 4 && (param.args[3] instanceof LocationListener)) {

                            LocationListener ll = (LocationListener) param.args[3];

                            Class<?> clazz = LocationListener.class;
                            Method m = null;
                            for (Method method : clazz.getDeclaredMethods()) {
                                if (method.getName().equals("onLocationChanged") && !Modifier.isAbstract(method.getModifiers())) {
                                    m = method;
                                    break;
                                }
                            }
                            Location l = new Location(LocationManager.GPS_PROVIDER);
                            l.setLatitude(latitude);
                            l.setLongitude(longtitude);
                            l.setAccuracy(10.00f);
                            l.setTime(0);
                        /*	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                l.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
							}*/
                            XposedHelpers.callMethod(ll, "onLocationChanged", l);
                            try {
                                if (m != null) {
                                    m.invoke(ll, l);
                                }
                            } catch (Exception e) {
                                XposedBridge.log(e);
                            }
                        }
                    }
                });
            }

            if (method.getName().equals("requestSingleUpdate ")
                    && !Modifier.isAbstract(method.getModifiers())
                    && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        if (param.args.length >= 3 && (param.args[1] instanceof LocationListener)) {

                            LocationListener ll = (LocationListener) param.args[3];

                            Class<?> clazz = LocationListener.class;
                            Method m = null;
                            for (Method method : clazz.getDeclaredMethods()) {
                                if (method.getName().equals("onLocationChanged") && !Modifier.isAbstract(method.getModifiers())) {
                                    m = method;
                                    break;
                                }
                            }

                            try {
                                if (m != null) {
                                    Location l = new Location(LocationManager.GPS_PROVIDER);
                                    l.setLatitude(latitude);
                                    l.setLongitude(longtitude);
                                    l.setAccuracy(100f);
                                    l.setTime(0);
                                /*	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                        l.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
									}*/
                                    m.invoke(ll, l);
                                }
                            } catch (Exception e) {
                                XposedBridge.log(e);
                            }
                        }
                    }
                });
            }
        }
    }
}
