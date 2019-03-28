package com.xinde.baggio;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.xinde.baggio.sandbox.BuildInfoGenerator;
import com.xinde.baggio.sandbox.CpuInfoGenerator;
import com.xinde.baggio.sandbox.DriverInfoGenerator;
import com.xinde.baggio.sandbox.RoPropGenerator;
import com.xinde.baggio.sandbox.SensorGenerator;
import com.xinde.baggio.sandbox.TelephonyGenerator;
import com.xinde.baggio.sandbox.WifiInfoGenerator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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

        // 8CKnDeCcXLY= 变换 n/J/73V2go8= hello
        // jebF7+JlGug= 变换 OyzYbZwQeAI=
//        String hello = desEny("world");
        Log.i(TAG, "onCreate: " + test("0901725664|93279e3308bdbbeed946fc965017f67a"));
    }

    public static Map<String, String> test(String paramString)
    {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("dataUnEncyption: ");
        ((StringBuilder)localObject).append(paramString);
        Log.e(TAG, ((StringBuilder)localObject).toString());

        HashMap localHashMap = new HashMap();
        paramString = desEny(paramString);
        localHashMap.put("data", encode(paramString));
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("data: ");
        localStringBuilder.append(paramString);
        Log.e(TAG, localStringBuilder.toString());

        localObject = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        localHashMap.put("request_time", "20190326034330");
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("|");
        localStringBuilder.append("20190326034330");
        localStringBuilder.append("|");
        localStringBuilder.append("66af02b97382f790c867c1ab6039758e");
        localHashMap.put("sign", md5(localStringBuilder.toString()));
        return localHashMap;
    }


    public static String desEny(String str) {
        if (str == null) {
            return "";
        }
        String a = "66af02b97382f790c867c1ab6039758e";
        Log.i(TAG, "desEny: ---" + Arrays.toString(a.getBytes()));
        try {
            byte[] md5s = MessageDigest.getInstance("md5").digest(a.getBytes("utf-8"));
            Log.i(TAG, "desEny: md5: " + Arrays.toString(md5s));
            Log.i(TAG, "desEny: md5: " + toHexString(md5s));
            byte[] copyOf = Arrays.copyOf(MessageDigest.getInstance("md5").digest(a.getBytes("utf-8")), 24);
//            System.out.println("1111: " + Arrays.toString(copyOf));
            Log.i(TAG, "before: " + Arrays.toString(copyOf));

            int i = 0;
            int i2 = 16;
            while (i < 8) {
                int i3 = i2 + 1;
                int i4 = i + 1;
                copyOf[i2] = copyOf[i];
                i2 = i3;
                i = i4;
            }

            Log.i(TAG, "after: " + Arrays.toString(copyOf));
            Key secretKeySpec = new SecretKeySpec(copyOf, "DESede");
            @SuppressLint("GetInstance") Cipher instance = Cipher.getInstance("DESede/ECB/PKCS7Padding");
            instance.init(1, secretKeySpec);
            Log.i(TAG, "desEny: " + Arrays.toString(instance.doFinal(str.getBytes("utf-8"))));

            return new String(Base64.encode(instance.doFinal(str.getBytes("utf-8")), 0));
//            return "";
//            return new String(Base64.encode(instance.doFinal(str.getBytes("utf-8")), 0));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHexString(byte[] keyData) {
        if (keyData == null) {
            return null;
        }
        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);
        for (byte aKeyData : keyData) {
            String hexStr = Integer.toString(aKeyData & 0x00FF, 16);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static String md5(String str) {
        Log.i(TAG, "md5: start=" + str);
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
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
