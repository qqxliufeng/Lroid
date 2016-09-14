package com.android.lf.lroid.v.fragment;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/2.
 */

public class HomeMoreFragment extends LroidBaseFragment {

    @BindView(R.id.vp_container)
    ViewPager mViewPager;

//    @BindView(R.id.tab_layout)
//    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.id_ctl_tool_bar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    public static HomeMoreFragment newInstance() {
        return new HomeMoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_layout;
    }

    @Override
    protected void initView(final View view) {
        mToolBar.setTitle("更多");
        mToolBar.setTitleTextColor(Color.WHITE);
        mToolBar.setNavigationIcon(R.drawable.img_app_top_back_icon);
//        mCollapsingToolbarLayout.setTitle("title");
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mCollapsingToolbarLayout.setStatusBarScrimColor(Color.TRANSPARENT);
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabTextColors(Color.WHITE,getResources().getColor(R.color.red));

//        view.findViewById(R.id.id_fb).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view,"点击",Snackbar.LENGTH_LONG).setAction("cancel", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                }).show();
//            }
//        });
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
            return "title "+(position+1);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}
