package com.android.lf.lroid.v.fragment;

import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.views.CustomDialogHelper;

/**
 * Created by feng on 2016/11/3.
 */
public class MoreFragment extends LroidBaseFragment{

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_layout;
    }

    @Override
    protected void initView(View view) {
        CustomDialogHelper.createEmptyDialog(mContext);
    }

    @Override
    protected void setComponent() {

    }
}
