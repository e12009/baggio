package com.xinde.baggio.hook.douyin;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.xinde.baggio.hook.AbstractHook;
import com.xinde.baggio.hook.faker.BaggioFaker;
import com.xinde.baggio.util.JsonUtil;
import com.xinde.baggio.util.ThreadPoolFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import okhttp3.ResponseBody;

/**
 * author: Shawn
 * time  : 1/14/19 3:41 PM
 * desc  :
 * update: Shawn 1/14/19 3:41 PM
 */
public class DouyinHook extends AbstractHook {
    private static final String TAG = "Hacker";
    private boolean flag = false;

    public DouyinHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);

    }

    @Override
    public void run() {
//        try {
//            Class<?> cls = getClassLoader().loadClass("com.bytedance.frameworks.core.encrypt.TTEncryptUtils");
//            XposedHelpers.findAndHookMethod(cls, "a", byte[].class, Integer.TYPE, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    Log.i(TAG, "afterHookedMethod: hook TTEncryptUtils-args=" + Arrays.toString(param.args));
//                    Log.i(TAG, "afterHookedMethod: hook TTEncryptUtils-result=" + param.getResult());
//                    Log.e(TAG, "afterHookedMethod: ", (new Exception(TAG + "1")));
////                    (new Exception(TAG + "1")).printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (!flag) {
            Log.d(TAG, "run: xixi");
            invokeNetMethod();
            flag = true;
        }

        try {                                               // com.ss.android.common.util.NetworkUtils.executePostRetry

            // https://aweme.snssdk.com/aweme/v1/user/contact/?cursor=0&count=20&type=0&ts=1552292247&js_sdk_version=1.6.4&app_type=normal&os_api=22&device_type=taobao1&device_platform=android&ssmix=a&iid=57546447339&manifest_version_code=400&dpi=240&uuid=471503770496316&version_code=400&app_name=aweme&version_name=4.0.0&openudid=13e5c9a39c02315d&device_id=61855332881&resolution=720*1208&os_version=5.1&language=en&device_brand=generic&ac=wifi&update_version_code=4002&aid=1128&channel=aweGW&_rticket=1552292248795&mcc_mnc=310260&as=a195e1e8f7f99c59066266&cp=159dc65771638194e1_cMg&mas=0194899350c966bf7fbf2a1f6ae8643f416c6c4c6c6c0c9cacc69c
            // [0, 0, https://aweme-eagle.snssdk.com/aweme/v1/user/?user_id=56938420578&retry_type=no_retry, true, null, null, true, null, true]
//            Method[] declaredMethods = cls.getDeclaredMethods();
//            for (Method declaredMethod : declaredMethods) {
//                Log.i(TAG, "run: method=" + declaredMethod);
//            } // com.ss.android.common.util.NetworkUtils.executeGetRetry
//            hookMethods("com.ss.android.common.util.NetworkUtils", "executeGet", new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    Log.i(TAG, "afterHookedMethod: hook NetworkUtils.executeGet-args=" + Arrays.toString(param.args));
//                    Log.i(TAG, "afterHookedMethod: hook NetworkUtils.executeGet-result=" + param.getResult());
//                    Log.e(TAG, "afterHookedMethod: ", (new Exception(TAG + "1")));
//                }
//            });
//            hookMethods("com.ss.android.common.util.NetworkUtils", "executePost", new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    Log.i(TAG, "afterHookedMethod: hook NetworkUtils.executePost-args=" + Arrays.toString(param.args));
//                    Log.i(TAG, "afterHookedMethod: hook NetworkUtils.executePost-result=" + param.getResult());
//                    Log.e(TAG, "afterHookedMethod: ", (new Exception(TAG + "2")));
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        // ------------------------------------------------------------------------------------------ //
//        hookEncrypt();
//        hookSearch();

//        DexClassLoader dexClassLoader = new DexClassLoader()
//        XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//
//                try {
//                    String activityName = param.thisObject.getClass().getSimpleName();
//                    Log.d(TAG, "afterHookedMethod: activityName=" + activityName);
//                    if (TextUtils.equals("AddFriendsActivity", activityName)) {
//                        ThreadPoolFactory.getInstance().execute(DouyinHook.this::hookApi);
//                    } else if (TextUtils.equals("ContactsActivity", activityName)) {
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    private void invokeNetMethod() {
        ThreadPoolFactory.getInstance().execute(() -> {
            try {
                Class<?> cls = getClassLoader().loadClass("com.ss.android.common.util.NetworkUtils");
                // (int i, int i2, String str, boolean z, List<com.ss.android.http.a.encode> list, f fVar, boolean z2, List<e> list2, boolean z3)
                // [0, 0, https://aweme.snssdk.com/aweme/v1/user/?user_id=94451677972, true, true, null, null, true]
                Method getRetryMethod = getMethod(cls, "executeGet");
                Method postRetryMethod = cls.getDeclaredMethod("executePost", Integer.TYPE, String.class, List.class);

                // com.ss.android.http.a.encode
                Class<?> pairCls = getClassLoader().loadClass("com.ss.android.http.a.encode.e");
                Constructor<?> constructor = pairCls.getConstructor(String.class, String.class);
                if (getRetryMethod != null) {
                    while (true) {
                        /**
                         * addresses : []
                         * emails : []
                         * instant_message_addresses : {}
                         * modification_date : 2019-01-14 12:05:52
                         * name : 1 872-515-7057
                         * phone_number : ["18725157057"]
                         * urls : []
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("addresses", Collections.emptyList());
                        params.put("emails", Collections.emptyList());
                        params.put("instant_message_addresses", Collections.emptyList());
                        params.put("urls", Collections.emptyList());
                        params.put("name", "Mary.chen");
                        params.put("phone_number", Collections.singletonList("18725157057")); // 18539504777 ["18725157057"]

                        // [{"addresses":[],"emails":[],"instant_message_addresses":{},"modification_date":"2019-03-11 06:33:18","name":"Jack Ma","phone_number":["18725157057"],"urls":[]}]

                        ArrayList<Object> list = new ArrayList<>();
                        list.add(constructor.newInstance("contact", "[{\"addresses\":[],\"emails\":[],\"instant_message_addresses\":{},\"modification_date\":\"2019-03-11 06:33:18\",\"name\":\"王建国\",\"phone_number\":[\"15359608766\"],\"urls\":[]}]"));
                        Log.i(TAG, "invokeNetMethod: params=" + list);

                        String uploadResult = (String) postRetryMethod.invoke(null, 0, "https://aweme.snssdk.com/aweme/v1/upload/contacts/?need_unregistered_user=0", list);
                        Log.i(TAG, "invokeNetMethod: uploadResult=" + uploadResult);
                        SystemClock.sleep(3000);
//
                        String res = (String) getRetryMethod.invoke(null, 0, 0, "https://aweme.snssdk.com/aweme/v1/user/contact/?cursor=0&count=20&type=0");
                        Log.i(TAG, "run: contactFriend=" + res);
                        SystemClock.sleep(2000);

                        ContactBean contactBean = JsonUtil.parseJsonToBean(res, ContactBean.class);
                        List<ContactBean.UserListBean> userList = contactBean.getUserList();
                        if (userList == null || userList.size() == 0) {
                            return;
                        }

                        ContactBean.UserListBean user = userList.get(0);
                        String uid = user.getUid();
                        String userInfo = (String) getRetryMethod.invoke(null, 0, 0, "https://aweme.snssdk.com/aweme/v1/user/?user_id=" + uid + "&retry_type=no_retry");
                        Log.i(TAG, "invokeNetMethod: userInfo=" + userInfo);
                        SystemClock.sleep(10000);
                    }
//                        String res = (String) getRetryMethod.invoke(null, 0, 0, "https://aweme.snssdk.com/aweme/v1/user/?user_id=56938420578", true, true, null, null, true);
//                String res = (String) getRetryMethod.invoke(null, 0, 0, "https://aweme.snssdk.com/aweme/v1/user/contact/?cursor=0&count=20&type=0", true, null, null, true, null, true);
                } else {
                    Log.i(TAG, "run: getRetryMethod is null");
                }
            } catch (Exception e) {
                Log.i(TAG, "run: stackTrace=" + Log.getStackTraceString(e));
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

    protected Method getMethod(Class<?> clazz, String methodName) {
        try {
            Log.d(TAG, "hookMethods: clazz --> " + Arrays.toString(clazz.getDeclaredMethods()));

            for (Method method : clazz.getDeclaredMethods()) {

                if (!TextUtils.equals(method.getName(), methodName)) continue;

                int modifiers = method.getModifiers();
                if (Modifier.isAbstract(modifiers)) continue;

                Log.d(TAG, "hookMethods: isPublic " + Modifier.isPublic(modifiers));
                if (!Modifier.isPublic(modifiers)) method.setAccessible(true);

                Log.d(TAG, "hookMethods: " + method);

                return method;
            }
        } catch (Exception e) {
            Log.e(TAG, "hookMethods: ", e);
        }
        return null;
    }

    static class Contact {

        /**
         * addresses : []
         * emails : []
         * instant_message_addresses : {}
         * modification_date : 2019-01-14 12:05:52
         * name : 1 872-515-7057
         * phone_number : ["18725157057"]
         * urls : []
         */

        private InstantMessageAddressesBean instant_message_addresses;
        private String modification_date;
        private String name;
        private List<?> addresses;
        private List<?> emails;
        private List<String> phone_number;
        private List<?> urls;

        public InstantMessageAddressesBean getInstant_message_addresses() {
            return instant_message_addresses;
        }

        public void setInstant_message_addresses(InstantMessageAddressesBean instant_message_addresses) {
            this.instant_message_addresses = instant_message_addresses;
        }

        public String getModification_date() {
            return modification_date;
        }

        public void setModification_date(String modification_date) {
            this.modification_date = modification_date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<?> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<?> addresses) {
            this.addresses = addresses;
        }

        public List<?> getEmails() {
            return emails;
        }

        public void setEmails(List<?> emails) {
            this.emails = emails;
        }

        public List<String> getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(List<String> phone_number) {
            this.phone_number = phone_number;
        }

        public List<?> getUrls() {
            return urls;
        }

        public void setUrls(List<?> urls) {
            this.urls = urls;
        }

        public static class InstantMessageAddressesBean {
        }
    }

}
