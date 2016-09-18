package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.activity.HomeActivity;

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, HomeActivity.class));
                finishActivity();
            }
        },3000);
    }

    @Override
    protected void setComponent() {

    }
}
