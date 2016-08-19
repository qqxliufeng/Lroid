package com.android.lf.lroid.v.fragment;

import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.p.common.CommonPresenter;

import javax.inject.Inject;

/**
 * Created by feng on 2016/8/19.
 */

public class LoginFragment extends BaseFragment {

    @Inject
    CommonPresenter commonPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_layout;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.id_bt_login).setOnClickListener(this);
    }

    @Override
    protected void setComponent() {
        LroidApplication.getInstance().getComponent().inject(this);
    }

    @Override
    public void onClick(View view) {
        commonPresenter.onLogin(0,10);
    }

}
