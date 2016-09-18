package com.android.lf.lroid.component;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.p.common.DataProvidePresenter;
import com.android.lf.lroid.p.common.HomePresenter;

import dagger.Module;
import dagger.Provides;


/**
 * Created by feng on 2016/8/17.
 */

@Module
public class PresentModule {

    @Provides
    CommonPresenter getCommonPresent(){
        return new CommonPresenter();
    }

    @Provides
    HomePresenter getHomePresent(){return new HomePresenter();}

    @Provides
    DataProvidePresenter getDataProvidePresenter(){
        return new DataProvidePresenter();
    }


}
