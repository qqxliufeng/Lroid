package com.android.lf.lroid.component;


import com.android.lf.lroid.p.CommonPresenter;
import com.android.lf.lroid.p.JieQiDataProvidePresenter;
import com.android.lf.lroid.p.JieRiDataProvidePresenter;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.p.UserHelperPresenter;

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
    JieQiDataProvidePresenter getJieQiDataProvidePresenter(){
        return new JieQiDataProvidePresenter();
    }

    @Provides
    JieRiDataProvidePresenter getJieRiDatatProvidePresenter(){
        return new JieRiDataProvidePresenter();
    }

    @Provides
    MobApiPresenter getMobApiPresenter(){
        return new MobApiPresenter();
    }

    @Provides
    UserHelperPresenter getUserHelperPresenter(){
        return new UserHelperPresenter();
    }


}
