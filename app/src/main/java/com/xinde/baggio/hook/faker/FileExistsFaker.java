package com.xinde.baggio.hook.faker;

import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.Constant;

import java.io.File;
import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;

/**
 * author: Shawn
 * time  : 2018/7/12 17:14
 * desc  :
 * update: Shawn 2018/7/12 17:14
 */
public class FileExistsFaker extends XC_MethodHook {
    private static final String TAG = "FileExistsFaker";

    protected Object resultObject;

    public FileExistsFaker() {
        this(new PenetrationObject());
    }

    public FileExistsFaker(Object object) {
        this.resultObject = object;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        printHookedMethod(param, true);

        // 如果此处不更改源方法返回值，则传入 PenetrationObject
        if (!(this.resultObject instanceof PenetrationObject)) {
            param.setResult(this.resultObject);
        }

        param = fakeParams(param);

        printHookedMethod(param, false);
    }


    protected MethodHookParam fakeParams(MethodHookParam param) {
        return param;
    }

    private void printHookedMethod(MethodHookParam param, boolean isBeforeHack) throws Exception {
        if (!Constant.CheckSwitch) return;

        Object object = param.thisObject;
        if (object == null) return;

        if (!(object instanceof File))
            throw new Exception("The object you hooked must be an instance of File");

        File file = (File) object;
        String className = file.getClass().getName();
        String methodName = param.method.getName();

        Object resultObject = param.getResult();
        String result;
        Class<?> returnType = ((Method) (param.method)).getReturnType();
        if (TextUtils.equals(returnType.getSimpleName(), "void")) {
            result = "void";
        } else {
            result = (resultObject == null ? "null" : resultObject.toString());
        }

        String absolutePath = file.getAbsolutePath();
        if (!absolutePath.startsWith("/data") && !absolutePath.startsWith("/storage")) { // 非系统数据 不打印
            if (isBeforeHack) {
                Log.i(TAG, String.format("before hack --> %s.%s('%s') = %s", className, methodName, file.getAbsolutePath(), result));
            } else {
                Log.i(TAG, String.format("after hack --> %s.%s('%s') = %s", className, methodName, file.getAbsolutePath(), result));
            }
        }
    }
}
