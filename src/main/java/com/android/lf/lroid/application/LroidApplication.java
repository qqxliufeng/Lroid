package com.android.lf.lroid.application;

import android.app.Application;

import com.android.lf.lroid.component.AppComponent;
import com.android.lf.lroid.component.AppModule;
//import com.android.lf.lroid.component.DaggerAppComponent;
//import com.android.lf.lroid.component.DaggerAppComponent;
//import com.android.lf.lroid.component.DaggerPresentComponent;
import com.android.lf.lroid.component.DaggerAppComponent;
import com.android.lf.lroid.component.DaggerPresentComponent;
import com.android.lf.lroid.component.PresentComponent;
import com.android.lf.lroid.component.ActivityModule;

/**
 * Created by feng on 2016/8/17.
 */

public class LroidApplication extends Application {

    private static LroidApplication myApplication;
    private AppComponent component;
    private PresentComponent presentComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        setComponent();
    }

    private void setComponent() {
        component = DaggerAppComponent.builder().activityModule(new ActivityModule()).build();
        presentComponent = DaggerPresentComponent.builder().appModule(new AppModule()).build();
    }

    public static LroidApplication getInstance(){
        return myApplication;
    }

    public AppComponent getComponent(){
        return component;
    }

    public PresentComponent getPresentComponent(){
        return presentComponent;
    }

}
