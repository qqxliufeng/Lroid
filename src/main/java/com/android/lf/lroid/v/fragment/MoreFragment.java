package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/9/2.
 */

public class MoreFragment extends BaseFragment {

    public static MoreFragment newInstance() {
        return  new MoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void setComponent() {

    }
}
