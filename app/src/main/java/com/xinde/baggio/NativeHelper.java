package com.xinde.baggio;

/**
 * author: Shawn
 * time  : 2018/6/22 17:47
 * desc  :
 * update: Shawn 2018/6/22 17:47
 */
public class NativeHelper {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
