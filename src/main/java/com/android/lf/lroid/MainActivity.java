package com.android.lf.lroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.m.interfaces.ILoginInterface;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.v.activity.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity{

    @Inject
    CommonPresenter commonPresenter;

    public void onClick(View view){
        commonPresenter.onLogin(0,10);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setComponent() {
        LroidApplication.getInstance().getComponent().inject(this);
    }
}
