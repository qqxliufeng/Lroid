package com.android.lf.lroid.component;

import com.android.lf.lroid.MainActivity;
import com.android.lf.lroid.v.fragment.BaseFragment;
import com.android.lf.lroid.v.fragment.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by feng on 2016/8/17.
 */
@Singleton
@Component(modules = { ActivityModule.class })
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginFragment baseFragment);

}
