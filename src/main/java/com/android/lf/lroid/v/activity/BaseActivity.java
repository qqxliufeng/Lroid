package com.android.lf.lroid.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.lf.lroid.interfaces.IPresentListener;

import butterknife.ButterKnife;


/**
 * Created by feng on 2016/8/1.
 */

public abstract class BaseActivity  extends AppCompatActivity implements IPresentListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //init butterKnife
        ButterKnife.bind(this);
        //设置代理
        setPresentComponent();
        initView();
    }

    public abstract int getLayoutId();

    public abstract void setPresentComponent();

    public abstract void initView();

    @Override
    public void onRequestStart(int requestID) {
    }

    @Override
    public void onRequestFail(int requestID,Throwable e) {

    }

    @Override
    public void onRequestSuccess(int requestID,String result) {

    }

    @Override
    public void onRequestEnd(int requestID) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
