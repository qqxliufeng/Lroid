package com.android.lf.lroid.p.common;

import android.os.Handler;
import android.util.Log;


import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.component.UserManagerService;
import com.android.lf.lroid.m.data.ContentBean;
import com.android.lf.lroid.m.common.LoginModelImpl;
import com.android.lf.lroid.m.interfaces.IBaseModelInterface;
import com.android.lf.lroid.m.interfaces.ILoginInterface;
import com.android.lf.lroid.m.interfaces.OnModelToPresenterListener;
import com.android.lf.lroid.v.interfaces.IBaseViewInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/8/1.
 */

public class CommonPresenter extends BasePresenter{

    public void onLogin(int start,int count){
        userManagerService.getData(start,count).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
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
