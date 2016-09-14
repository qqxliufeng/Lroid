package com.android.lf.lroid.v.fragment;

import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.p.common.CommonPresenter;

import javax.inject.Inject;

/**
 * Created by feng on 2016/8/19.
 */

public class LoginFragment extends LroidBaseFragment {

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
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onClick(View view) {
        commonPresenter.requestData(0x0,0,10);
    }

}
