package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.data.JieQiData;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.activity.SplashActivity;
import com.android.lf.lroid.v.views.IndicatorView;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class NavigationFragment extends LroidBaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.id_vp_fragment_navigation_container)
    ViewPager mViewPager;
    @BindView(R.id.id_tv_fragment_navigation_jump)
    TextView mJump;
    @BindView(R.id.id_ll_fragment_navigation_index_container)
    LinearLayout mIndexContainer;

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
        initIndicator();
        mViewPager.setOffscreenPageLimit(textIds.length);
        mViewPager.setAdapter(new NavigationViewPagerAdapter(getChildFragmentManager()));
        mViewPager.addOnPageChangeListener(this);
        mJump.setOnClickListener(this);
    }

    private void initIndicator() {
        for (int i = 0; i < textIds.length; i++) {
            IndicatorView indicatorView = new IndicatorView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
            params.leftMargin = 10;
            params.rightMargin = 10;
            indicatorView.setLayoutParams(params);
            indicatorView.setIndicatorColor(Color.RED);
            mIndexContainer.addView(indicatorView, i);
            if (i == 0) {
                indicatorView.setSelect(true);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int count = mIndexContainer.getChildCount();
        if (position == count - 1) {
            mIndexContainer.setVisibility(View.GONE);
            return;
        }
        mIndexContainer.setVisibility(View.VISIBLE);
        for (int i = 0; i < count; i++) {
            if (position % count == i) {
                ((IndicatorView) mIndexContainer.getChildAt(i)).setSelect(true);
            } else {
                ((IndicatorView) mIndexContainer.getChildAt(i)).setSelect(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
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
