package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.data.JieRiData;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.p.JieRiDataProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/2.
 */

public class HomeMoreFragment extends LroidBaseFragment {

    @BindView(R.id.vp_container)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.id_ctl_tool_bar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.id_al_fragment_more_top)
    AppBarLayout mAppBarLayout;

    @Inject
    JieRiDataProvidePresenter providePresenter;

    private ProgressDialog progressDialog;

    private boolean isFirstLoad = true;

    public static HomeMoreFragment newInstance() {
        return new HomeMoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_layout;
    }

    @Override
    protected void initView(final View view) {
        mToolBar.setTitle("节日");
        mToolBar.setTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        providePresenter.setFragment(this);

    }

    @Override
    public void onRequestStart(int requestID) {
        progressDialog = ProgressDialog.show(mContext, "", "正在加载……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if ((Integer) result != 0) {
            mViewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
            mViewPager.setOffscreenPageLimit(JieRiData.jieRiTitles.length);
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isFirstLoad){
            isFirstLoad = false;
            providePresenter.fillDataToDB(DataProvider.FEAST_URI);
        }
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return IndexMoreListFragment.newInstance(JieRiData.jieRiTitles[position]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return JieRiData.jieRiTitles[position];
        }

        @Override
        public int getCount() {
            return JieRiData.jieRiTitles.length;
        }
    }

}
