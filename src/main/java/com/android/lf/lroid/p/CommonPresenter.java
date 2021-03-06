package com.android.lf.lroid.p;

import android.util.Log;



import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/8/1.
 */

public class CommonPresenter extends BasePresenter {

    public void requestData(int requestID, int start, int count){
        requestMethod1(requestID);
//        requestMethod2(requestID,start,count);
    }

    private void requestMethod2(final int requestID,final int start, final int count) {
        userManagerService.getData(start,count).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return userManagerService.getData(start,count);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                if (checkNullPresent()){
                    getPresentListener().onRequestStart(requestID);
                }else {
                    Log.e("TAG","is null");
                }
            }

            @Override
            public void onCompleted() {
                if (checkNullPresent()){
                    getPresentListener().onRequestEnd(requestID);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (checkNullPresent()){
                    getPresentListener().onRequestFail(requestID,e);
                }
            }

            @Override
            public void onNext(String s) {
                if (checkNullPresent()){
                    getPresentListener().onRequestSuccess(requestID,s);
                }
            }
        });
    }

    private void requestMethod1(final int requestID) {
        userManagerService.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
                if (checkNullPresent()){
                    getPresentListener().onRequestStart(requestID);
                }else {
                    Log.e("TAG","is null");
                }
            }

            @Override
            public void onCompleted() {
                if (checkNullPresent()){
                    getPresentListener().onRequestEnd(requestID);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (checkNullPresent()){
                    getPresentListener().onRequestFail(requestID,e);
                }
            }

            @Override
            public void onNext(String s) {
                if (checkNullPresent()){
                    getPresentListener().onRequestSuccess(requestID,s);
                }
            }
        });
    }
}
