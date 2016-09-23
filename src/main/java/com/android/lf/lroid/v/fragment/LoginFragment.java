package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.p.common.CommonPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/8/19.
 */

public class LoginFragment extends LroidBaseFragment {

    public static LoginFragment newInstance() {
        return  new LoginFragment();
    }

    @BindView(R.id.id_tl_fragment_login)
    TabLayout mTabLayout;

    @BindView(R.id.id_vp_fragment_login_container)
    ViewPager mViewPager;

    private String[] titles = new String[]{"快速登录","帐号登录"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_layout;
    }

    @Override
    protected void initView(View view) {
        mViewPager.setAdapter(new LoginViewPagerAdapter(getChildFragmentManager()));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void setComponent() {
    }

    class LoginViewPagerAdapter extends FragmentStatePagerAdapter{


        public LoginViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1)
                return LoginNormalFragment.newInstance();
            return LoginFastFragment.newInstance();
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }


}
