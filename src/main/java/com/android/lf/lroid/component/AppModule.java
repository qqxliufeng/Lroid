package com.android.lf.lroid.component;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by feng on 2016/8/19.
 */

@Module
public class AppModule {

    @Provides
    public OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60 * 1000,TimeUnit.MILLISECONDS);
        builder.readTimeout(60 * 1000,TimeUnit.MILLISECONDS);
        return builder.build();
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient okHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.baseUrl("https://api.douban.com/v2/movie/");
        return builder.build();
    }

    @Provides
    public UserManagerService getUserService(Retrofit retrofit){
        return retrofit.create(UserManagerService.class);
    }

}
