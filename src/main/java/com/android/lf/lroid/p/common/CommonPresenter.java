package com.android.lf.lroid.p.common;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.m.data.ContentBean;
import com.android.lf.lroid.m.common.LoginModelImpl;

import java.util.ArrayList;


import dagger.Module;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/8/1.
 */

public class CommonPresenter extends BasePresenter{


    public void onLogin(int start,int count){
//        requestMethod1(start, count);
        requestMethod2(start,count);
    }

    private void requestMethod2(final int start, final int count) {
        userManagerService.getData(start,count).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                Log.e("TAG","flagMap  s is ----------------->   "+Thread.currentThread().getName());
                return userManagerService.getData(start,count);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                if (checkNullPresent()){
                    iPresentListener.onRequestStart();
                }else {
                    Log.e("TAG","is null");
                }
            }

            @Override
            public void onCompleted() {
                if (checkNullPresent()){
                    iPresentListener.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (checkNullPresent()){
                    iPresentListener.onRequestFail(e);
                }
            }

            @Override
            public void onNext(String s) {
                if (checkNullPresent()){
                    iPresentListener.onRequestSuccess(s);
                }
            }
        });
    }

    private void requestMethod1(int start, int count) {
        userManagerService.getData(start,count).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
                if (checkNullPresent()){
                    iPresentListener.onRequestStart();
                }else {
                    Log.e("TAG","is null");
                }
            }

            @Override
            public void onCompleted() {
                if (checkNullPresent()){
                    iPresentListener.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (checkNullPresent()){
                    iPresentListener.onRequestFail(e);
                }
            }

            @Override
            public void onNext(String s) {
                if (checkNullPresent()){
                    iPresentListener.onRequestSuccess(s);
                }
            }
        });
    }
}
