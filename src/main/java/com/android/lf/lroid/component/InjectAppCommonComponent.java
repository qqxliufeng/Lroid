package com.android.lf.lroid.component;


import com.android.lf.lroid.p.BasePresenter;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by feng on 2016/8/19.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface InjectAppCommonComponent {

    void inject(BasePresenter basePresenter);

}
