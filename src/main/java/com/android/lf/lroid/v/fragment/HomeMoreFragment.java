package com.android.lf.lroid.v.fragment;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/10/11.
 */

public class HomeMoreFragment extends LroidBaseFragment{

    public static HomeMoreFragment newInstance() {
        return new HomeMoreFragment();
    }

    @BindView(R.id.id_tl_fragment_more_bar)
    Toolbar mToolBar;

    @BindView(R.id.id_ctl_tool_bar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.id_nsv_fragment_more_content)
    NestedScrollView mNestedScrollView;

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
        if (!hidden && isFirstLoad) {
            isFirstLoad = false;
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.id_fl_fragment_home_more_content_one, MoreContentOneFragment.newInstance());
            fragmentTransaction.replace(R.id.id_fl_fragment_home_more_content_two, MoreContentTwoFragment.newInstance());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @OnClick(R.id.id_iv_fragment_more_top_pic)
    public void onTopPicClick(){
        MethodUtils.startFragmentsActivity(mContext,"传统节日", FragmentContainerActivity.JIE_RI_FLAG);
    }
}
