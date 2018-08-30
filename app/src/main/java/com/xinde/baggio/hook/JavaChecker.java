package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import java.io.File;
import java.io.FileInputStream;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * author: Shawn
 * time  : 2018/6/26 14:03
 * desc  :
 * update: Shawn 2018/6/26 14:03
 */
@Deprecated
public class JavaChecker extends AbstractHook {
    private static final String TAG = "JavaChecker";

    public JavaChecker(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        Log.i(TAG, "run: start JavaChecker");
//        hook_Build_getString();
//        hook_SystemProperty_get();
//        hook_GL_glGetString();

        hook_File_constructor();
        hook_File_method();

//        hook_Runtime_exec();
//        hook_ProcessBuilder_command();

//        hook_NetworkInfo_getType();
//        hook_Bluetooth();

//        hook_Wifi();
//        hookInputStream();

//        hook_Pattern();
    }

    private void hook_Pattern() {
//        hookMethod("java.util.regex.Pattern", "matches",
//                String.class, CharSequence.class, new BaggioFaker());

        hookMethods("java.util.regex.Pattern", "matches", new BaggioFaker());
    }

    private void hookInputStream() {
        try {
            XposedBridge.hookAllConstructors(FileInputStream.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object[] objs = param.args;
                    if (objs[0] instanceof String) {
                        Log.i(TAG, "afterHookedMethod: " + objs[0]);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hook_Wifi() {
        try {

            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getMacAddress", new XC_MethodHook() {

                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            super.afterHookedMethod(param);
                            Log.i(TAG, "afterHookedMethod: WifiInfo.getMacAddress -> " + param.getResult());
//                            param.setResult(SharedPref.getXValue("WifiMAC"));
                        }

                    });

            // 内网IP
            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getIpAddress", new XC_MethodHook() {

                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            super.afterHookedMethod(param);
                            Log.i(TAG, "afterHookedMethod: WifiInfo.getIpAddress -> " + param.getResult());

//                            param.setResult(SharedPref.getIntXValue("getIP"));
                            // param.setResult(tryParseInt(SharedPref.getXValue("getIP")));

                        }

                    });

            XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", this.getClassLoader(),
                    "getSSID", new XC_MethodHook() {

                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            super.afterHookedMethod(param);
                            Log.i(TAG, "afterHookedMethod: WifiInfo.getSSID -> " + param.getResult());

//                            param.setResult(SharedPref.getXValue("WifiName"));
                        }

                    });


        } catch (Exception e) {
            Log.e(TAG, "hook_Wifi: ", e);
        }
    }

    private void hook_Bluetooth() {
        XposedHelpers.findAndHookMethod(
                "android.bluetooth.BluetoothAdapter", this.getClassLoader(),
                "getDefaultAdapter", new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Object result = param.getResult();
                        Log.i(TAG, "afterHookedMethod: 00000 -> " + result);
//                        param.setResult(SharedPref.getXValue("LYMAC"));
                    }

                });
        XposedHelpers.findAndHookMethod(
                "android.bluetooth.BluetoothAdapter", this.getClassLoader(),
                "getAddress", new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Object result = param.getResult();
                        Log.i(TAG, "afterHookedMethod: 11111 -> " + result);
//                        param.setResult(SharedPref.getXValue("LYMAC"));
                    }

                });
        // 双层 MAC
        XposedHelpers.findAndHookMethod(
                "android.bluetooth.BluetoothDevice", this.getClassLoader(),
                "getAddress", new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        // super.afterHookedMethod(param);
//                        param.setResult(SharedPref.getXValue("LYMAC"));
                        Object result = param.getResult();
                        Log.i(TAG, "afterHookedMethod: 22222 -> " + result);
                    }

                });
    }


    private void hook_NetworkInfo_getType() {
        hookMethods("android.net.NetworkInfo", "getType", new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object result = param.getResult();
                Log.i(TAG, "afterHookedMethod: " + result); // wifi = 1
            }
        });
    }

    private void hook_ProcessBuilder_command() {
        hookMethods("java.lang.ProcessBuilder", "command", new BaggioFaker());
//        XposedBridge.hookAllMethods(ProcessBuilder.class, "command", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                super.beforeHookedMethod(param);
//                Object[] args = param.args;
//                for (Object arg : args) {
//                    if (arg instanceof List) {
//                        Log.i(TAG, "afterHookedMethod: args -> " + arg);
//                    } else {
//                        Log.i(TAG, "afterHookedMethod: arg -> " + arg);
//                    }
//                }
//            }
//        });
    }


    private void hook_Runtime_exec() {
//        Runtime.getRuntime().exec("");
//        XposedBridge.hookAllMethods(Runtime.class, "exec", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//
//                Object[] args = param.args;
//                for (Object arg : args) {
//                    if (arg instanceof String[]) {
//                        Log.i(TAG, "afterHookedMethod: args -> " + Arrays.toString((String[]) arg));
//                    } else {
//                        Log.i(TAG, "afterHookedMethod: arg -> " + arg);
//                    }
//                }
////                File f = (File) param.thisObject;
////                if (!f.getAbsolutePath().startsWith("/data/data")) {
////                    Log.i(TAG, "afterHookedMethod: list -> " + f.getAbsolutePath());
////                }
//            }
//        });


        // alipay 未调用
        XposedHelpers.findAndHookMethod("java.lang.Runtime", this.getClassLoader(), "exec",
                String.class, String[].class, File.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        File f = (File) param.args[2];
                        if (f != null) {
                            Log.i(TAG, "afterHookedMethod: "+f.getAbsolutePath());
                        }
                    }
                });

        // alipay 未调用
        XposedHelpers.findAndHookMethod("java.lang.Runtime", this.getClassLoader(), "exec",
                String[].class, String[].class, File.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        File f = (File) param.args[2];
                        if (f != null) {
                            Log.i(TAG, "afterHookedMethod: "+f.getAbsolutePath());
                        }
                    }
                });
//
    }

    private void hook_File_method() {
        XposedBridge.hookAllMethods(File.class, "list", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                File f = (File) param.thisObject;
                if (!f.getAbsolutePath().startsWith("/data/data") && !f.getAbsolutePath().startsWith("/storage")) {
                    Log.i(TAG, "afterHookedMethod: list -> " + f.getAbsolutePath());
                    new Exception().printStackTrace();
                }
            }
        });

        XposedBridge.hookAllMethods(File.class, "listFiles", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                File f = (File) param.thisObject;
                if (!f.getAbsolutePath().startsWith("/data/data") && !f.getAbsolutePath().startsWith("/storage")) {
                    Log.i(TAG, "afterHookedMethod: listFiles -> " + f.getAbsolutePath());
                    new Exception().printStackTrace();
                }
            }
        });

        XposedBridge.hookAllMethods(File.class, "exists", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                File f = (File) param.thisObject;
                if (!f.getAbsolutePath().startsWith("/data/data") && !f.getAbsolutePath().startsWith("/storage")) {
                    Log.i(TAG, "afterHookedMethod: exists -> " + f.getAbsolutePath());
                    new Exception().printStackTrace();
                }
            }
        });
    }

    private void hook_File_constructor() {
        XposedBridge.hookAllConstructors(File.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                File f = (File) param.thisObject;
                if (!f.getAbsolutePath().startsWith("/data/data") && !f.getAbsolutePath().startsWith("/storage")) {
                    Log.i(TAG, "afterHookedMethod: " + f.getAbsolutePath());
                    new Exception().printStackTrace();
                }
            }
        });


    }

    private void hook_GL_glGetString() {
        XposedHelpers.findAndHookMethod("com.google.android.gles_jni.GLImpl", this.getClassLoader(), "glGetString", Integer.TYPE, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Log.i(TAG, "hook_GL_glGetString: ");
            }

        });
    }

    private void hook_Build_getString() {
        XposedHelpers.findAndHookMethod("android.os.Build", this.getClassLoader(), "getString", String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (param.args.length > 0) {
                    String key = (String) param.args[0];
                    Log.i(TAG, "hook_Build_getString: " + key);
                }
            }
        });
    }

    private void hook_SystemProperty_get() {
        hookMethods("android.os.SystemProperties", "get", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (param.args.length > 0) {
                    String key = (String) param.args[0];
                    Log.i(TAG, "hook_SystemProperty_get: " + key);
                }
            }
        });
    }
}
