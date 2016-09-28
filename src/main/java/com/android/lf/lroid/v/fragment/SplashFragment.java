package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.p.UserHelperPresenter;
import com.android.lf.lroid.utils.Constants;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Created by feng on 2016/9/14.
 */

public class SplashFragment extends LroidBaseFragment {

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }
    @Inject
    UserHelperPresenter mUserHelperPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash_layout;
    }

    @Override
    protected void initView(View view) {
        mUserHelperPresenter.setFragment(this);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!TextUtils.isEmpty(PreferenceUtils.getPrefString(mContext, Constants.USER_NAME_FLAG,""))) {
                    mUserHelperPresenter.normalLogin(PreferenceUtils.getPrefString(mContext,Constants.USER_NAME_FLAG,""),PreferenceUtils.getPrefString(mContext,Constants.USER_PASSWORD_FLAG,""));
                }else {
                    startActivity(new Intent(mContext, HomeActivity.class));
                    finishActivity();
                }
            }
        },1500);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {

        startActivity(new Intent(mContext, HomeActivity.class));
        finishActivity();
    }

}
