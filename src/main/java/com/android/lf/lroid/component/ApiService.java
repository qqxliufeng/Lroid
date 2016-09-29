package com.android.lf.lroid.component;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by feng on 2016/8/19.
 */

public interface ApiService {

    @GET("top250")
    public Observable<String> getData(@Query("start") int start, @Query("count") int count);

    @FormUrlEncoded
    @POST
    public Observable<String> getData(@FieldMap Map<String,Object> params);

}
