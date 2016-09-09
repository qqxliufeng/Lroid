package com.android.lf.lroid.v.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.fragment.ListFragment;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/7.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.vp_container)
    ViewPager mViewPager;

//    @BindView(R.id.tab_layout)
//    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.id_ctl_tool_bar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test_layout;
    }

    @Override
    public void setPresentComponent() {
    }

    @Override
    public void initView() {
        mToolBar.setTitle("更多");
        mToolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mCollapsingToolbarLayout.setTitle("title");
        mCollapsingToolbarLayout.setTitleEnabled(true);
        mCollapsingToolbarLayout.setTitle("更多");
        mCollapsingToolbarLayout.setStatusBarScrimColor(Color.TRANSPARENT);
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabTextColors(Color.WHITE,getResources().getColor(R.color.red));
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ListFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "title "+(position+1);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
