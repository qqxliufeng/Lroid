package com.android.lf.lroid.v.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.activity.HomeActivity;

/**
 * Created by feng on 2016/9/2.
 */

public class HomeMineFragment extends BaseFragment {

    public static HomeMineFragment newInstance() {
        return new HomeMineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void setComponent() {

    }
}
