package com.xinde.baggio;


/**
 * author: Shawn
 * time  : 2018/6/27 10:28
 * desc  :
 * update: Shawn 2018/6/27 10:28
 */
public interface SPKey {

    interface PropKey {
        String BRAND = "BRAND";
        String BOARD = "BOARD";
        String CPU_ABI = "CPU_ABI";
        String CPU_ABI2 = "CPU_ABI2";
        String DEVICE = "DEVICE";
        String DISPLAY = "DISPLAY";
        String FINGERPRINT = "FINGERPRINT";
        String HARDWARE = "HARDWARE";
        String ID = "ID";
        String MANUFACTURER = "MANUFACTURER";
        String MODEL = "MODEL";
        String PRODUCT = "PRODUCT";
        String BOOTLOADER = "BOOTLOADER";
        String HOST = "HOST";
        String TAGS = "TAGS";
        String TYPE = "TYPE";
        String USER = "USER";
        String TIME = "TIME";
        String RADIO = "RADIO";
        String SERIAL = "SERIAL";
        String NAME = "NAME";

        // Build.VERSION
        String INCREMENTAL = "INCREMENTAL";
        String RELEASE = "RELEASE";
        String SDK = "SDK";
        String CODENAME = "CODENAME";
        String SDK_INT = "SDK_INT";

        String IS_EMULATOR = "IS_EMULATOR"; // 对应 ro.kernel.qemu 属性

        String ANDROID_ID = "ANDROID_ID";
        String DESCRIPTION = "DESCRIPTION";
        String BASE_BAND = "BASE_BAND"; // 对应 gsm.version.baseband 属性
    }


    interface WifiInfo {
        String SSID = "SSID";
        String BSSID = "BSSID";
        String MAC_ADDRESS = "MAC_ADDRESS";
        String IP_ADDRESS = "IP_ADDRESS";
    }


    interface Telephony {
        String DEVICE_ID = "DEVICE_ID";
        String LINE1_NUMBER = "LINE1_NUMBER";
        String NETWORK_TYPE = "NETWORK_TYPE";
        String NETWORK_OPERATOR_NAME = "NETWORK_OPERATOR_NAME";
        String SIM_OPERATOR_NAME = "SIM_OPERATOR_NAME";
        String SIM_SERIAL_NUMBER = "SIM_SERIAL_NUMBER";
        String SUBSCRIBER_ID = "SUBSCRIBER_ID";
    }


    interface SensorKey {
        String TYPE_ALL = "TYPE_ALL";
        String TYPE_ACCELEROMETER = "TYPE_ACCELEROMETER";
        String TYPE_GYROSCOPE = "TYPE_GYROSCOPE";
        String TYPE_GRAVITY = "TYPE_GRAVITY";
    }
}
