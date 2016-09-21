package com.android.lf.lroid.application;

import android.app.Application;

import com.android.lf.lroid.component.AppModule;
import com.android.lf.lroid.component.DaggerInjectAppCommonComponent;
import com.android.lf.lroid.component.InjectAppCommonComponent;
import com.android.lf.lroid.volley.RequestManager;
import com.mob.mobapi.MobAPI;

import cn.smssdk.SMSSDK;

/**
 * Created by feng on 2016/8/17.
 */

public class LroidApplication extends Application {

    private static LroidApplication myApplication;
    private InjectAppCommonComponent presentComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        setComponent();
        RequestManager.init(this);
        MobAPI.initSDK(this,"1741a9799f4c2");
        SMSSDK.initSDK(this,"17421312f6704","88316ee60bc51947ba6f90098d05e3b7");
    }

    private void setComponent() {
        presentComponent = DaggerInjectAppCommonComponent.builder().appModule(new AppModule()).build();
    }

    public static LroidApplication getInstance(){
        return myApplication;
    }

    public InjectAppCommonComponent getPresentComponent(){
        return presentComponent;
    }

}
