package com.android.lf.lroid.component;

import com.android.lf.lroid.p.common.CommonPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by feng on 2016/8/17.
 */

@Module
public class ActivityModule {

    @Provides
    CommonPresenter getCommonPresent(){
        return new CommonPresenter();
    }


}
