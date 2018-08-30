package com.xinde.baggio.hook;

import android.util.Log;

import com.xinde.baggio.hook.faker.BaggioFaker;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/22 17:52
 * desc  :
 * update: Shawn 2018/6/22 17:52
 */
public class OpenGLHook extends AbstractHook {
    private static final String TAG = "OpenGLHook";

    public OpenGLHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeOpenGL();

        Log.i(TAG, TAG + " running...");
    }


    private void fakeOpenGL() {
        try { // Alipay 未调用
            XposedHelpers.findAndHookMethod("com.google.android.gles_jni.GLImpl", this.getClassLoader(), "glGetString", Integer.TYPE, new BaggioFaker() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
//                    if (param.args[0] != null) {
//                        if (param.args[0].equals(Integer.valueOf(7936))) {
//                            param.setResult(SharedPref.getXValue("GLVendor"));
//                        }
//                        if (param.args[0].equals(Integer.valueOf(7937))) {
//                            param.setResult(SharedPref.getXValue("GLRenderer"));
//                        }
//                    }
                }

            });
        } catch (Exception e) {
            Log.e(TAG, "fakeOpenGL: ", e);
        }
    }
}
