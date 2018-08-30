package com.xinde.baggio.hook.faker;

import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.Constant;

import java.lang.reflect.Method;
import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;

/**
 * author: Shawn
 * time  : 2018/7/12 17:12
 * desc  :
 * update: Shawn 2018/7/12 17:12
 */
public class BaggioFaker extends XC_MethodHook {
    private static final String TAG = "BaggioFaker";

    protected Object resultObject;

    public BaggioFaker() {
        this(new PenetrationObject());
    }

    public BaggioFaker(Object object) {
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

        param = fakeParam(param);

        printHookedMethod(param, false);
    }


    protected MethodHookParam fakeParam(MethodHookParam param) {
        return param;
    }

    protected void printHookedMethod(MethodHookParam param, boolean isBeforeHack) {
        if (!Constant.CheckSwitch) return;

        if (param.thisObject != null) {
            String className = param.thisObject.getClass().getName();
            String methodName = param.method.getName();
            Object[] args = param.args;

            String vars;
            if (args != null) {
                if (args.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Object arg : args) {
                        if (arg instanceof String[]) {
                            sb.append(String.format("'%s'", Arrays.toString((String[]) arg))).append(", ");
                        } else {
                            if (arg == null) continue;
                            sb.append(String.format("'%s'", arg.toString())).append(", ");
                        }
                    }
                    vars = sb.substring(0, sb.length() - 2);
                } else {
                    vars = "";
                }
            } else {
                vars = "";
            }

            Object resultObject = param.getResult();
            String result;
            Class<?> returnType = ((Method) (param.method)).getReturnType();
            if (TextUtils.equals(returnType.getSimpleName(), "void")) {
                result = "void";
            } else {
                result = (resultObject == null ? "null" : resultObject.toString());
            }

            if (isBeforeHack) {
                Log.i(TAG, String.format("before hack --> %s.%s(%s) = %s", className, methodName, vars, result));
            } else {
                Log.i(TAG, String.format("after hack --> %s.%s(%s) = %s", className, methodName, vars, result));
            }
        }
    }
}
