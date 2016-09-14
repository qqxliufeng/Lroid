package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.lf.lroid.R;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class NavigationFragment extends LroidBaseFragment {

    @BindView(R.id.id_vp_fragment_navigation_container)
    ViewPager mViewPager;

    private int[] imgIds = new int[]{R.drawable.img_navigation_one,R.drawable.img_navigation_two,R.drawable.img_navigation_three};

    public static NavigationFragment newInstance() {
        return  new NavigationFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_layout;
    }

    @Override
    protected void initView(View view) {
        mViewPager.setOffscreenPageLimit(imgIds.length);
        mViewPager.setAdapter(new NavigationViewPagerAdapter(getChildFragmentManager()));
    }

    @Override
    protected void setComponent() {
    }

    class NavigationViewPagerAdapter extends FragmentStatePagerAdapter{

        public NavigationViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == imgIds.length -1){
                return NavigationItemFragment.newInstance(imgIds[position],true);
            }
            return NavigationItemFragment.newInstance(imgIds[position],false);
        }

        @Override
        public int getCount() {
            return imgIds.length;
        }
    }

}
