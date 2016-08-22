package com.android.lf.lroid.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.lf.lroid.interfaces.IPresentListener;
import com.android.lf.lroid.p.common.BasePresenter;
import com.android.lf.lroid.v.interfaces.IBaseViewInterface;


/**
 * Created by feng on 2016/8/1.
 */

public abstract class BaseActivity  extends AppCompatActivity implements IPresentListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setComponent();
        initView();
    }

    public abstract int getLayoutId();

    public abstract void setComponent();

    public abstract void initView();

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestFail(Throwable e) {

    }

    @Override
    public void onRequestSuccess(String result) {

    }

    @Override
    public void onRequestEnd() {

    }
}
