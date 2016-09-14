package com.android.lf.lroid.v.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.fragment.NavigationFragment;
import com.android.lf.lroid.v.fragment.SplashFragment;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class SplashActivity extends BaseActivity {

    private boolean isNavigation = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_layout;
    }
    @Override
    public void setPresentComponent() {}

    @Override
    public void initView() {
        setSwipeBackEnable(false);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isNavigation){
            fragmentTransaction.replace(R.id.id_fl_activity_splash_fragment_container, NavigationFragment.newInstance());
        }else {
            fragmentTransaction.replace(R.id.id_fl_activity_splash_fragment_container, SplashFragment.newInstance());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
