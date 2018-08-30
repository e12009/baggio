package com.xinde.baggio.sandbox;

import android.content.Context;
import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.util.IoUtil;
import com.xinde.baggio.util.ShellUtil;

/**
 * author: Shawn
 * time  : 2018/7/11 11:55
 * desc  :
 * update: Shawn 2018/7/11 11:55
 */
public class RoPropGenerator extends AbstractGenerator {
    private static final String TAG = "RoPropGenerator";

    public RoPropGenerator(Context context) {
        super(context);
    }

    @Override
    public void run() {
        fakeProp();

        Log.i(TAG, TAG + " running...");
    }

    private void fakeProp() {
        new Thread(() -> {
            String fileDir = this.context.getFilesDir().getAbsolutePath();
            IoUtil.copyFileFromAssert(this.context, Constant.MPROP_NAME, fileDir);
            String mpropAbsPath = String.format("%s/%s", fileDir, Constant.MPROP_NAME);
            ShellUtil.execCommand(String.format("chmod 770 %s/%s", fileDir, Constant.MPROP_NAME), true, false);
            try {
                ShellUtil.CommandResult commandResult = ShellUtil.execCommand("./" + mpropAbsPath, true, false);
                if (commandResult.errorMsg == null) {
                    ShellUtil.execCommand("setprop ro.product.cpu.abi armeabi", true, false);
                    ShellUtil.execCommand("setprop ro.product.cpu.abilist armeabi-v7a,armeabi", true, false);
                    ShellUtil.execCommand("setprop ro.product.cpu.abilist32 armeabi-v7a,armeabi", true, false);
                    ShellUtil.execCommand("setprop ro.kernel.qemu 0", true, false);
                    Log.i(TAG, "fakeProp: fake success");
                } else {
                    Log.e(TAG, "run: " + commandResult.errorMsg);
                }
            } catch (Exception e) {
                Log.e(TAG, "run: ", e);
            }
        }).start();
    }
}
