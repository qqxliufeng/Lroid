package com.android.lf.lroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.component.ActivityModule;
import com.android.lf.lroid.component.DaggerAppComponent;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.v.activity.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity{

    @Inject
    CommonPresenter commonPresenter;
    private TextView tv_content;

    private ProgressDialog progressDialog;

    public void onClick(View view){
        commonPresenter.onLogin(0,10);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setComponent() {
        DaggerAppComponent.builder().activityModule(new ActivityModule()).build().inject(this);
    }

    @Override
    public void initView() {
        commonPresenter.setPresentListener(this);
        tv_content = (TextView) this.findViewById(R.id.id_tv_content);
    }

    @Override
    public void onRequestStart() {
        progressDialog= ProgressDialog.show(this,null,"正在加载……");
    }

    @Override
    public void onRequestSuccess(String result) {
        tv_content.setText(result);
    }

    @Override
    public void onRequestEnd() {
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
