package com.android.lf.lroid.v.fragment;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.database.DataProvider;

import butterknife.BindView;

/**
 * Created by feng on 2016/10/11.
 */

public class HomeMoreFragment extends LroidBaseFragment {

    public static HomeMoreFragment newInstance() {
        return new HomeMoreFragment();
    }

    @BindView(R.id.id_tl_fragment_more_bar)
    Toolbar mToolBar;

    @BindView(R.id.id_ctl_tool_bar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private boolean isFirstLoad = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_more_layout;
    }

    @Override
    protected void initView(View view) {
        mToolBar.setTitle("更多");
        mToolBar.setTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setTitleEnabled(false);
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isFirstLoad){
            isFirstLoad = false;
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.id_fl_fragment_home_more_content_one,MoreContentOneFragment.newInstance());
            fragmentTransaction.replace(R.id.id_fl_fragment_home_more_content_two,MoreContentTwoFragment.newInstance());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
