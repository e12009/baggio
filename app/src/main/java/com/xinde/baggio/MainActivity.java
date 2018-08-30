package com.xinde.baggio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xinde.baggio.sandbox.BuildInfoGenerator;
import com.xinde.baggio.sandbox.CpuInfoGenerator;
import com.xinde.baggio.sandbox.DriverInfoGenerator;
import com.xinde.baggio.sandbox.RoPropGenerator;
import com.xinde.baggio.sandbox.SensorGenerator;
import com.xinde.baggio.sandbox.TelephonyGenerator;
import com.xinde.baggio.sandbox.WifiInfoGenerator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BuildInfoGenerator(getApplicationContext()).run();
        new CpuInfoGenerator(getApplicationContext()).run();
        new DriverInfoGenerator(getApplicationContext()).run();
        new WifiInfoGenerator(getApplicationContext()).run();
        new TelephonyGenerator(getApplicationContext()).run();
        new RoPropGenerator(getApplicationContext()).run();
        new SensorGenerator(getApplicationContext()).run();
    }


//    private void setLocationInfo() {
//        mySP.setFloatSharedPref("lat", (float) 30.2425140000); // 纬度
//        mySP.setFloatSharedPref("log", (float) 120.1404220000); // 经度
//    }
//
//    private void setGraphInfo() {
//        mySP.setSharedPref("GLRenderer", "Adreno (TM) 111"); // GPU
//        mySP.setSharedPref("GLVendor", "UFU");// GPU厂商
//    }
}
