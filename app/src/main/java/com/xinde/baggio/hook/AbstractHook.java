package com.xinde.baggio.hook;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/6/25 13:53
 * desc  :
 * update: Shawn 2018/6/25 13:53
 */
public abstract class AbstractHook {
    private static final String TAG = "AbstractHook";

    protected XC_LoadPackage.LoadPackageParam loadPackageParam;

    public AbstractHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
    }

    protected ClassLoader getClassLoader() {
        return this.loadPackageParam.classLoader;
    }

    public abstract void run();


    /**
     * hook 某些重载私有方法
     *
     * @param className
     * @param methodName
     * @param xmh
     */
    protected void hookMethods(String className, String methodName, XC_MethodHook xmh) {
        try {
            Class<?> clazz = this.getClassLoader().loadClass(className);

            for (Method method : clazz.getDeclaredMethods()) {

                if (!TextUtils.equals(method.getName(), methodName)) continue;

                int modifiers = method.getModifiers();
                if (Modifier.isAbstract(modifiers)) continue;

                if (Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers)) {
                    method.setAccessible(true);
                }

                XposedBridge.hookMethod(method, xmh);
            }
        } catch (Exception e) {
            Log.e(TAG, "hookMethods: ", e);
        }
    }

    /**
     * hook
     *
     * @param className
     * @param methodName
     * @param parameterTypesAndCallback
     */
    protected void hookMethod(String className, String methodName, Object... parameterTypesAndCallback) {
        try {
            Class<?> clazz = this.getClassLoader().loadClass(className);

            Class<?>[] paramsType = getParameterClasses(this.getClassLoader(), parameterTypesAndCallback);
            Method method = clazz.getDeclaredMethod(methodName, paramsType);
            if (method == null) return;

            int modifiers = method.getModifiers();
            if (Modifier.isAbstract(modifiers)) return;

            if (Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers)) {
                method.setAccessible(true);
            }

            XC_MethodHook callback = (XC_MethodHook) parameterTypesAndCallback[parameterTypesAndCallback.length - 1];
            XposedBridge.hookMethod(method, callback);
        } catch (Exception e) {
            Log.e(TAG, "hookMethod: ", e);
        }
    }


    protected static Class<?>[] getParameterClasses(ClassLoader classLoader, Object[] parameterTypesAndCallback) throws Exception {
        Class<?>[] parameterClasses = null;
        for (int i = parameterTypesAndCallback.length - 1; i >= 0; i--) {
            Object type = parameterTypesAndCallback[i];
            if (type == null) throw new Exception("parameter type must not be null");

            // ignore trailing callback
            if (type instanceof XC_MethodHook) continue;

            if (parameterClasses == null) parameterClasses = new Class<?>[i + 1];

            if (type instanceof Class) {
                parameterClasses[i] = (Class<?>) type;
            } else if (type instanceof String) {
                parameterClasses[i] = XposedHelpers.findClass((String) type, classLoader);
            } else {
                throw new Exception("parameter type must either be specified as Class or String");
            }
        }

        // if there are no arguments for the method
        if (parameterClasses == null) parameterClasses = new Class<?>[0];

        return parameterClasses;
    }
}
