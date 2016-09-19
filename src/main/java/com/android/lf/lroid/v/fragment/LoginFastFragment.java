package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/9/18.
 */

public class LoginFastFragment extends LroidBaseFragment {


    public static LoginFastFragment newInstance() {
        return new LoginFastFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_fast_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void setComponent() {

    }
}
