package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
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

    private String[] textIds;
    private int[] bgColors = new int[]{R.color.bg_color_one, R.color.bg_color_two, R.color.bg_color_three};

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_layout;
    }

    @Override
    protected void initView(View view) {
        textIds = getResources().getStringArray(R.array.navigation_content_array);
        mViewPager.setOffscreenPageLimit(textIds.length);
        mViewPager.setAdapter(new NavigationViewPagerAdapter(getChildFragmentManager()));
        mJump.setOnClickListener(this);
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_tv_fragment_navigation_jump) {
            PreferenceUtils.setPrefBoolean(mContext, SplashActivity.IS_NAVIGATION_FLAG, false);
            startActivity(new Intent(mContext, HomeActivity.class));
            finishActivity();
        }
    }

    class NavigationViewPagerAdapter extends FragmentStatePagerAdapter {

        public NavigationViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == textIds.length - 1) {
                return NavigationItemFragment.newInstance(textIds[position], bgColors[position], true);
            }
            return NavigationItemFragment.newInstance(textIds[position], bgColors[position], false);
        }

        @Override
        public int getCount() {
            return textIds.length;
        }
    }

}
