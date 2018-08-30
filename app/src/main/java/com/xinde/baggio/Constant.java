package com.xinde.baggio;

/**
 * author: Shawn
 * time  : 2018/6/25 14:04
 * desc  :
 * update: Shawn 2018/6/25 14:04
 */
public interface Constant {
    boolean CheckSwitch = true;

    String PACKAGE_NAME = "com.xinde.baggio";
    String FAKER_FILE_NAME = "faker";

    // TODO: 2018/7/12 此处 hardcode，genymotion 动态获取有问题
    String SD_PATH = "/sdcard";
    String TMP_PATH = "/data/local/tmp";
    String FAKE_PATH = SD_PATH + "/" + "baggio";

    String CPUINFO_FILE_NAME = "cpuinfo";
    String CPUINFO_PATH = "/proc/cpuinfo";

    String VERSION_FILE_NAME = "version";
    String BUILD_VERSION_PATH = "/proc/version";

    String BUILD_PROP_NAME = "build.prop";
    String BUILD_PROP_PATH = "/system/build.prop";

    String DRIVERS_FILE_NAME = "drivers";
    String DRIVERS_FILE_PATH = "/proc/tty/drivers";

    String MPROP_NAME = "mprop";
}
