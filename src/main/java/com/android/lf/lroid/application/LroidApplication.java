package com.android.lf.lroid.application;

import android.app.Application;

import com.android.lf.lroid.component.AppComponent;
import com.android.lf.lroid.component.AppModule;
import com.android.lf.lroid.component.DaggerPresentComponent;
import com.android.lf.lroid.component.PresentComponent;
import com.android.lf.lroid.component.ActivityModule;

/**
 * Created by feng on 2016/8/17.
 */

public class LroidApplication extends Application {

    private static LroidApplication myApplication;
    private PresentComponent presentComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        setComponent();
    }

    private void setComponent() {
        presentComponent = DaggerPresentComponent.builder().appModule(new AppModule()).build();
    }

    public static LroidApplication getInstance(){
        return myApplication;
    }

    public PresentComponent getPresentComponent(){
        return presentComponent;
    }

}
