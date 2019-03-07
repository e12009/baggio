package com.xinde.baggio.hook.douyin;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.SPKey;
import com.xinde.baggio.hook.AbstractHook;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.util.JsonUtil;
import com.xinde.baggio.util.SharedPref;
import com.xinde.baggio.util.ThreadPoolFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;

/**
 * author: Shawn
 * time  : 1/14/19 3:41 PM
 * desc  :
 * update: Shawn 1/14/19 3:41 PM
 */
public class DouyinHook extends AbstractHook {
    private static final String TAG = "DouyinHook";

    public DouyinHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);

    }

    @Override
    public void run() {
        Log.d(TAG, "run: ");
//        hookEncrypt();
//        hookSearch();

        XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                try {
                    String activityName = param.thisObject.getClass().getSimpleName();
                    Log.d(TAG, "afterHookedMethod: activityName=" + activityName);
                    if (TextUtils.equals("AddFriendsActivity", activityName)) {
                        ThreadPoolFactory.getInstance().execute(DouyinHook.this::hookApi);
                    } else if (TextUtils.equals("ContactsActivity", activityName)) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void reflectRetrofit() {
        try {
            // https://api.amemv.com/aweme/v1/user/contact/?cursor=0&count=20&type=0&ts=1551943041&js_sdk_version=1.6.4&app_type=normal&os_api=22&device_type=taobao1&device_platform=android&ssmix=a&iid=57546447339&manifest_version_code=400&dpi=240&uuid=471503770496316&version_code=400&app_name=aweme&version_name=4.0.0&openudid=13e5c9a39c02315d&device_id=61855332881&resolution=720*1208&os_version=5.1&language=en&device_brand=generic&ac=wifi&update_version_code=4002&aid=1128&channel=aweGW&_rticket=1551943041287&mcc_mnc=310260&as=a1556c4871886cc5e06833&cp=c68bc85e12008c55e1Wc_g&mas=01585c459994ac3f4f87fccba8e92e7faccccc1c6c0ca6acc6c66c
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("https://api.amemv.com")
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .build();
            // com.ss.android.ugc.aweme.framework.services.ServiceManager
            Class<?> serviceManagerCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.framework.services.ServiceManager");
            Method getMethod = serviceManagerCls.getDeclaredMethod("get");
            Object serviceManager = getMethod.invoke(null);
            Method getServiceMethod = serviceManagerCls.getDeclaredMethod("getService", Class.class);
            // com.ss.android.ugc.aweme.framework.services.IRetrofitService
            Class<?> iRetrofitServiceCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.framework.services.IRetrofitService");
            Object iRetrofitService = getServiceMethod.invoke(serviceManager, iRetrofitServiceCls);
            Method createNewRetrofitMethod = iRetrofitServiceCls.getDeclaredMethod("createNewRetrofit", String.class);
            Object retrofit = createNewRetrofitMethod.invoke(iRetrofitService, "https://aweme.snssdk.com");
            // com.ss.android.ugc.aweme.framework.services.IRetrofit
            Class<?> iRetrofitCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.framework.services.IRetrofit");
            Method createMethod = iRetrofitCls.getDeclaredMethod("create", Class.class);

            Class<?> friendsApiCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.friends.api.FriendApi");
            DouyinService douyinService = (DouyinService) createMethod.invoke(retrofit, DouyinService.class);
            ResponseBody responseBody = douyinService.queryContactsFriends(0, 20, 0).get();
//            Response<?> response = call.execute();
            Log.i(TAG, "reflectRetrofit: stringResult===========" + responseBody.string());
        } catch (Exception e) {
            Log.e(TAG, "reflectRetrofit: stackTrace=" + Log.getStackTraceString(e));
        }
        Log.i(TAG, "reflectRetrofit: running...");
    }

    private void reflectApi() {
        try {
            Class<?> friendsApiCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.friends.api.a");
            Method mm = friendsApiCls.getDeclaredMethod("a");
            Object invoke = mm.invoke(null);
            Log.i(TAG, "reflectApi: invoke=" + invoke);

            // com.ss.android.ugc.aweme.friends.api
            Class<?> fApiCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.friends.api.FriendApi");
            Method mm2 = fApiCls.getDeclaredMethod("queryContactsFriends", Integer.TYPE, Integer.TYPE, Integer.TYPE);
            Object invoke1 = mm2.invoke(invoke, 0, 20, 0);
            Log.i(TAG, "hookApi: invoke1================" + invoke1);
            Class<?> resultCls = getClassLoader().loadClass("a.i");
            Method mm3 = resultCls.getDeclaredMethod("a", Callable.class);
            Object invoke2 = mm3.invoke(invoke1, new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            });
            Log.i(TAG, "reflectApi: result=============" + JsonUtil.parseBeanToJson(invoke2));
        } catch (Exception e) {
            Log.i(TAG, "reflectApi: stackStace=" + Log.getStackTraceString(e));
        }
        Log.i(TAG, "reflectApi: running...");
    }

    private void hookApi() {
        try {
            Class<?> uploadContactCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.friends.api.e");
            XposedHelpers.findAndHookMethod(uploadContactCls, "a", List.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    super.afterHookedMethod(methodHookParam);
                    Log.i(TAG, "afterHookedMethod: uploadContact.args=" + Arrays.toString(methodHookParam.args));
                    Log.i(TAG, "afterHookedMethod: uploadContact.result=" + methodHookParam.getResult());
                }
            });

            Class<?> queryContactCls = getClassLoader().loadClass("com.ss.android.ugc.aweme.friends.model.ContactFriendModel");
            XposedHelpers.findAndHookMethod(queryContactCls, "fetchList", Integer.TYPE, Integer.TYPE, Integer.TYPE, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    super.afterHookedMethod(methodHookParam);
                    Log.i(TAG, "afterHookedMethod: queryContact.args=" + Arrays.toString(methodHookParam.args));
                    Log.i(TAG, "afterHookedMethod: queryContact.result=" + methodHookParam.getResult());
//                    reflectApi();
//                    reflectApi();
                    ThreadPoolFactory.getInstance().execute(DouyinHook.this::reflectRetrofit);
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "hookApi: stackTrace=" + Log.getStackTraceString(e));
        }
        Log.i(TAG, "hookApi: running...");
    }

    private void hookSearch() {
        try {
            hookMethods(" com.ss.android.ugc.aweme.discover.api.SearchApi", "a", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d(TAG, "afterHookedMethod: " + param);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "hookSearch: running");
    }

    private void hookEncrypt() {
        try {
            hookMethod("com.bytedance.frameworks.core.encrypt.a", "a", String.class, List.class, new BaggioFaker());
//            Class<?> cls = this.getClassLoader().loadClass("com.bytedance.frameworks.core.encrypt.a");
//            XposedHelpers.findAndHookMethod(cls, "a", String.class, List.class, new BaggioFaker());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "hookEncrypt: running");
    }

    private void hookLog() {
        try {
            Class<?> cls = this.getClassLoader().loadClass("com.bytedance.utils.LogUtils");
            XposedHelpers.findField(cls, "sIsLoggable").set(null, true);
            Log.d(TAG, "run: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
