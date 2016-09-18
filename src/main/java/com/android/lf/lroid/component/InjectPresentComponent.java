package com.android.lf.lroid.component;

import com.android.lf.lroid.v.activity.MainActivity;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.fragment.HomeIndexFragment;
import com.android.lf.lroid.v.fragment.IndexListFragment;
import com.android.lf.lroid.v.fragment.IndexTopFragment;
import com.android.lf.lroid.v.fragment.LoginFastFragment;
import com.android.lf.lroid.v.fragment.LoginFragment;
import com.android.lf.lroid.v.fragment.LoginNormalFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by feng on 2016/8/17.
 */
@Singleton
@Component(modules = { PresentModule.class })
public interface InjectPresentComponent {

    void inject(MainActivity mainActivity);

    void inject(HomeActivity homeActivity);

    void inject(IndexTopFragment indexFragment);

    void inject(IndexListFragment indexListFragment);

    void inject(LoginNormalFragment loginNormalFragment);

    void inject(LoginFastFragment loginFastFragment);

}
