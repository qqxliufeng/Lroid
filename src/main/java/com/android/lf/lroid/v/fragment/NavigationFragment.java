package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.activity.SplashActivity;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class NavigationFragment extends LroidBaseFragment {

    @BindView(R.id.id_vp_fragment_navigation_container)
    ViewPager mViewPager;
    @BindView(R.id.id_tv_fragment_navigation_jump)
    TextView mJump;

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
        mJump.setOnClickListener(this);
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_tv_fragment_navigation_jump){
            PreferenceUtils.setPrefBoolean(mContext, SplashActivity.IS_NAVIGATION_FLAG,false);
            startActivity(new Intent(mContext, HomeActivity.class));
            finishActivity();
        }
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
