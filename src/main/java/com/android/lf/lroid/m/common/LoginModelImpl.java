package com.android.lf.lroid.m.common;

import android.os.SystemClock;
import android.util.Log;


import com.android.lf.lroid.m.data.ContentBean;
import com.android.lf.lroid.m.interfaces.IBaseModelInterface;
import com.android.lf.lroid.m.interfaces.ILoginInterface;
import com.android.lf.lroid.m.interfaces.OnModelToPresenterListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/8/1.
 */

public class LoginModelImpl{



    public void onLoadingData(String url, final OnModelToPresenterListener onModelToPresenterListener) {
        new Thread() {
            @Override
            public void run() {
                ArrayList<ContentBean> list = new ArrayList<ContentBean>();
                for (int i = 0; i <10; i++) {
                    ContentBean content = new ContentBean();
                    SystemClock.sleep(500);
                    content.setContent("index is ---------->  " + i);
                    list.add(content);
                }
                onModelToPresenterListener.onModelToPresenter(list);
            }
        }.start();
    }

    public void login(final int start, int count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ILoginInterface iLoginInterface = retrofit.create(ILoginInterface.class);
        iLoginInterface.login(start,count).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
                Log.e("TAG","start running   " + Thread.currentThread().getName());
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Log.e("TAG", " onNext running  "+s);
            }
        });
    }
}
