package com.xinde.baggio.hook;

import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.hook.faker.BaggioFaker;

import java.util.Arrays;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Shawn
 * time  : 2018/7/10 16:40
 * desc  :
 * update: Shawn 2018/7/10 16:40
 */
public class RuntimeHook extends AbstractHook {
    private static final String TAG = "RuntimeHook";

    public RuntimeHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {
        fakeRuntimeExec();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeRuntimeExec() {
        XposedBridge.hookAllMethods(Runtime.class, "exec", new BaggioFaker() {
            @Override
            protected MethodHookParam fakeParam(MethodHookParam param) {
                Object[] args = param.args;
                if (args.length != 1) return param;

                String fakePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.CPUINFO_FILE_NAME);
                if (args[0] instanceof String[]) {
                    String[] commands = (String[]) args[0];
                    if (TextUtils.equals("[cat, /proc/cpuinfo, |, grep, Serial]", Arrays.toString(commands))) {
                        param.args[0] = new String[]{"cat", fakePath, "|", "grep", "Serial"};
                    } else if (TextUtils.equals("[ps]", Arrays.toString(commands))) {
                        param.args[0] = new String[]{"ps", "|", "grep", "-vE", "'genymotion|su|xposed'"};
                    }
                } else if (args[0] instanceof String) {
                    String command = (String) args[0];
                    if (TextUtils.equals(command, "cat /proc/cpuinfo | grep Serial")) {
                        param.args[0] = String.format("cat %s | grep Serial", fakePath);
                    }
                }
                return param;
            }
        });
    }
}
