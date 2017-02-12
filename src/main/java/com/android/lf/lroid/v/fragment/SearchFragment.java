package com.android.lf.lroid.v.fragment;

import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.views.CustomDialogHelper;

/**
 * Created by feng on 2016/11/3.
 */

public class SearchFragment extends LroidBaseFragment {

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_layout;
    }

    @Override
    protected void initView(View view) {
        CustomDialogHelper.createEmptyDialog(mContext);
    }

    @Override
    protected void setComponent() {

    }
}
