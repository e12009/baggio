package com.xinde.baggio.sandbox;

import android.content.Context;
import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.util.IoUtil;

/**
 * author: Shawn
 * time  : 2018/7/6 18:37
 * desc  :
 * update: Shawn 2018/7/6 18:37
 */
public class DriverInfoGenerator extends AbstractGenerator {
    private static final String TAG = "DriverInfoGenerator";

    public DriverInfoGenerator(Context context) {
        super(context);
    }

    @Override
    public void run() {
        createDeviceFile();

        Log.i(TAG, TAG + " running...");
    }

    private void createDeviceFile() {
        String content =
                "/dev/tty             /dev/tty        5       0 system:/dev/tty\n" +
                "/dev/console         /dev/console    5       1 system:console\n" +
                "/dev/ptmx            /dev/ptmx       5       2 system\n" +
                "rfcomm               /dev/rfcomm   216 0-255 serial\n" +
                "g_serial             /dev/ttyGS    235 0-3 serial\n" +
                "usbserial            /dev/ttyUSB   188 0-511 serial\n" +
                "acm                  /dev/ttyACM   166 0-31 serial\n" +
                "smd_tty_driver       /dev/smd      243 0-36 serial\n" +
                "msm_serial_hs        /dev/ttyHS    244 0-255 serial\n" +
                "pty_slave            /dev/pts      136 0-1048575 pty:slave\n" +
                "pty_master           /dev/ptm      128 0-1048575 pty:master\n";
        String filePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.DRIVERS_FILE_NAME);
        IoUtil.writeFile(filePath, content);
    }
}
