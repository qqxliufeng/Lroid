package com.android.lf.lroid.v.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.fragment.NavigationFragment;
import com.android.lf.lroid.v.fragment.SplashFragment;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class SplashActivity extends BaseActivity {

    public static final String IS_NAVIGATION_FLAG = "is_navigation_flag";

    private boolean isNavigation = true;
    private static boolean NAVIGATION_FLAG = true;
    private static boolean CONTROLLER_FLAG = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    public void setPresentComponent() {}

    @Override
    public void initView() {

        //
        setSwipeBackEnable(false);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        isNavigation = (PreferenceUtils.getPrefBoolean(this, IS_NAVIGATION_FLAG, true) || (MethodUtils.isCurrentVersion(this) && NAVIGATION_FLAG)) && CONTROLLER_FLAG;
        if (isNavigation) {
            fragmentTransaction.replace(R.id.id_fl_activity_splash_fragment_container, NavigationFragment.newInstance());
        } else {
            fragmentTransaction.replace(R.id.id_fl_activity_splash_fragment_container, SplashFragment.newInstance());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
