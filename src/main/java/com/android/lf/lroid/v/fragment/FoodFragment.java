package com.android.lf.lroid.v.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.lf.lroid.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodFragment extends LroidBaseFragment {

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    @BindView(R.id.id_tl_fragment_food_tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.id_vp_fragment_food_container)
    ViewPager mContentContainer;

    private ArrayList<String> titles = new ArrayList<String>() {
        {
            add("title1");
            add("title1");
            add("title1");
            add("title1");
            add("title1");
            add("title1");
            add("title1");
            add("title1");


        }
    };

    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_food_layout;
    }

    @Override
    protected void initView(View view) {
        mTabTitle.setTabMode(TabLayout.MODE_FIXED);
        mTabTitle.setTabTextColors(Color.BLACK, ContextCompat.getColor(mContext, R.color.colorPrimary));
        myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        mContentContainer.setAdapter(myViewPagerAdapter);
        mTabTitle.setupWithViewPager(mContentContainer);
    }

    @Override
    protected void setComponent() {
    }


    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }

}
