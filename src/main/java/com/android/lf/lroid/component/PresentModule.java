package com.android.lf.lroid.component;


import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.p.common.JieQiDataProvidePresenter;
import com.android.lf.lroid.p.common.HomePresenter;
import com.android.lf.lroid.p.common.JieRiDataProvidePresenter;

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
    JieQiDataProvidePresenter getJieQiDataProvidePresenter(){
        return new JieQiDataProvidePresenter();
    }

    @Provides
    JieRiDataProvidePresenter getJieRiDatatProvidePresenter(){
        return new JieRiDataProvidePresenter();
    }


}
