package com.android.lf.lroid.component;

import com.android.lf.lroid.p.common.BasePresenter;
import com.android.lf.lroid.p.common.CommonPresenter;

import dagger.Component;


/**
 * Created by feng on 2016/8/19.
 */
@Component(modules = {AppModule.class})
public interface PresentComponent {

    void inject(BasePresenter basePresenter);

}
