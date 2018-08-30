package com.xinde.baggio.hook.faker;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * author: Shawn
 * time  : 2018/7/6 14:10
 * desc  :
 * update: Shawn 2018/7/6 14:10
 */
public class RedirectUtil {

    public static void redirectFromFile(String oriFilePath, String destFilePath) {
        XposedBridge.hookAllConstructors(File.class, new FileRedirectFaker(oriFilePath, destFilePath));
    }

    public static void redirectFromInputStream(String oriFilePath, String destFilePath) {
        XposedBridge.hookAllConstructors(FileInputStream.class, new InputStreamRedirectFaker(oriFilePath, destFilePath));
    }

    /**
     * todo test
     *
     * @param loader
     * @param oriFilePath
     * @param destFilePath
     */
    public static void redirectFromRuntime(ClassLoader loader, String oriFilePath, String destFilePath) {
        XposedHelpers.findAndHookMethod("java.lang.Runtime", loader, "exec",
                String[].class, String[].class, File.class, new FileRedirectFaker(oriFilePath, destFilePath));
    }

    /**
     * todo test
     *
     * @param loader
     * @param oriFilePath
     * @param destFilePath
     */
    public static void redirectFromPattern(ClassLoader loader, final String oriFilePath, final String destFilePath) {
        XposedHelpers.findAndHookMethod("java.util.regex.Pattern", loader, "matcher", CharSequence.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)
                            throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param.args.length == 1) {
                            if (param.args[0].equals(oriFilePath)) {
                                param.args[0] = destFilePath;
                            }
                        }
                    }

                });
    }

    /**
     * todo test
     *
     * @param oriFilePath
     * @param destFilePath
     */
    public static void redirectFromProcessBuilder(final String oriFilePath, final String destFilePath) {
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(
                ProcessBuilder.class, String[].class),
                new XC_MethodHook() {

                    protected void beforeHookedMethod(MethodHookParam param)
                            throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param.args[0] != null) {
                            String[] strArr = (String[]) param.args[0];
                            String str = "";
                            for (String str2 : strArr) {
                                str = new StringBuilder(String.valueOf(str))
                                        .append(str2).append(":")
                                        .toString();
                                if (TextUtils.equals(str2, oriFilePath)) {
                                    strArr[1] = destFilePath;
                                }

                            }
                            param.args[0] = strArr;
                        }
                    }

                });
    }
}
