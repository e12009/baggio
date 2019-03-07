package com.xinde.baggio.hook.douyin;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: Shawn
 * time  : 3/7/19 3:03 PM
 * desc  :
 * update: Shawn 3/7/19 3:03 PM
 */
public interface DouyinService {

    @GET("/aweme/v1/user/contact/")
    K<ResponseBody> queryContactsFriends(@Query("cursor") int i, @Query("count") int i2, @Query("type") int i3);
}
