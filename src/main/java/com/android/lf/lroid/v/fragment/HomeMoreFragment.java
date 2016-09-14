package com.android.lf.lroid.v.fragment;

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
import com.android.lf.lroid.m.data.JieQiData;
import com.android.lf.lroid.m.data.JieRiData;
import com.android.lf.lroid.v.views.AppBarStateChangeListener;

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
        mViewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void setComponent() {
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ListFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return JieRiData.jieRiNames[position];
        }

        @Override
        public int getCount() {
            return JieRiData.jieRiNames.length;
        }
    }

}
