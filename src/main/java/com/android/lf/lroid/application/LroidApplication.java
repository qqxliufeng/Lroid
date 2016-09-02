package com.android.lf.lroid.application;

import android.app.Application;

import com.android.lf.lroid.component.AppModule;
import com.android.lf.lroid.component.DaggerInjectAppCommonComponent;
import com.android.lf.lroid.component.InjectAppCommonComponent;

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
