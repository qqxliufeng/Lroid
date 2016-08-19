package com.android.lf.lroid.m.interfaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by feng on 2016/8/1.
 */

public interface ILoginInterface{

    @GET("top250")
    public Observable<String> login(@Query("start") int start, @Query("count") int count);

}
