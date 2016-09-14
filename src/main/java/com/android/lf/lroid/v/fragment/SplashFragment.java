package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/9/14.
 */

public class SplashFragment extends LroidBaseFragment {

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void setComponent() {

    }
}
