package com.xinde.baggio.hook.faker;

import android.util.Log;

import com.xinde.baggio.Constant;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;

/**
 * author: Shawn
 * time  : 2018/7/12 17:15
 * desc  :
 * update: Shawn 2018/7/12 17:15
 */
public class FileRedirectFaker extends XC_MethodHook {
    private static final String TAG = "FileRedirectFaker";

    private String oriFilePath;
    private String destFilePath;

    public FileRedirectFaker(String oriFilePath, String destFilePath) {
        this.oriFilePath = oriFilePath;
        this.destFilePath = destFilePath;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        // just for test
//        Object object = param.thisObject;
//        if (object == null) return;
//
//        File f = (File) object;
//        String path = f.getAbsolutePath();
//        if (!path.contains("/storage") || !path.contains("/data/data")) {
//            new Exception().printStackTrace();
//        }

//        if (!(object instanceof File))
//            throw new Exception("The object you hooked must be an instance of File");
//        Object a = param.args[0];
//        if (a instanceof String) {
//            String b = (String) a;
//            if (b.contains("cpu") || b.contains()) {
//                new Exception().printStackTrace();
//            }
//        }
        if (param.args.length == 1) {
            if (param.args[0].equals(this.oriFilePath)) {
                param.args[0] = this.destFilePath;

                printRedirectInfo();
            }
        } else if (param.args.length == 2 && !File.class.isInstance(param.args[0])) {
            int i = 0;
            String str = "";
            while (i < 2) {
                String stringBuilder;
                if (param.args[i] != null) {
                    if (param.args[i].equals(this.oriFilePath)) {
                        param.args[i] = this.destFilePath;

                        printRedirectInfo();
                    }

                    stringBuilder = new StringBuilder(
                            String.valueOf(str))
                            .append(param.args[i])
                            .append(":").toString();
                } else {
                    stringBuilder = str;
                }
                i++;
                str = stringBuilder;
            }
        }
    }


    private void printRedirectInfo() {
        if (Constant.CheckSwitch) {
            String format = String.format("redirect File: %s -> %s", this.oriFilePath, this.destFilePath);
            Log.i(TAG, "redirect File: " + format);
        }
    }
}
