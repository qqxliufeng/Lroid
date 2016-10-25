package com.android.lf.lroid.v.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.fragment.EntertainmentMoreListFragment;
import com.android.lf.lroid.v.fragment.FoodFragment;
import com.android.lf.lroid.v.fragment.HealthFragment;
import com.android.lf.lroid.v.fragment.HealthSearchFragment;
import com.android.lf.lroid.v.fragment.HealthSearchResultFragment;
import com.android.lf.lroid.v.fragment.HistoryTodayFragment;
import com.android.lf.lroid.v.fragment.LoginFragment;
import com.android.lf.lroid.v.fragment.PersonalInfoFragment;
import com.android.lf.lroid.v.fragment.PhotoSelectFragment;
import com.android.lf.lroid.v.fragment.ResetPasswordFragment;
import com.android.lf.lroid.v.fragment.SetPersonalInfoFragment;
import com.android.lf.lroid.v.fragment.WebContentFragment;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/13.
 */

public class FragmentContainerActivity extends BaseActivity {

    public static final String TITLE_FLAG = "title_flag";
    public static final String FRAGMENT_FLAG = "fragment_flag";
    public static final String EXTRA_FLAG = "extra_flag";

    public static final int WEB_CONTENT_CONTAINER_FLAG = 0x0;
    public static final int LOGIN_FRAGMENT= 0x1;
    public static final int PERSONAL_INFO_FRAGMENT_FLAG = 0x2;
    public static final int PHOTO_SELECT_FRAGMENT_FLAG = 0x3;
    public static final int SET_PERSONAL_INFO_FRAGMENT_FLAG = 0x4;
    public static final int RESET_PASSWORD_FLAG = 0x5;
    public static final int HISTORY_TODAY_FLAG = 0x6;
    public static final int ENTERTAINMENT_FLAG = 0x7;
    public static final int HEALTH_FLAG = 0x8;
    public static final int HEALTH_SEARCH_FLAG = 0x9;
    public static final int HEALTH_SEARCH_RESULT_FLAG = 0x10;
    public static final int FOOD_FLAG = 0x11;



    @BindView(R.id.id_tl_app_top_bar)
    Toolbar mToolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment_container_layout;
    }

    @Override
    public void setPresentComponent() {
    }

    @Override
    public void initView() {
        getSwipeBackLayout().setEdgeSize(100);
        initFragment();
    }

    private void initFragment() {
        mToolBar.setTitle(getIntent().getStringExtra(TITLE_FLAG));
        mToolBar.setTitleTextColor(Color.WHITE);
        mToolBar.setNavigationIcon(R.drawable.img_app_top_back_icon);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int fragmentId = getIntent().getIntExtra(FRAGMENT_FLAG,0x0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (fragmentId){
            case WEB_CONTENT_CONTAINER_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, WebContentFragment.newInstance(getIntent().getBundleExtra(EXTRA_FLAG)));
                break;
            case LOGIN_FRAGMENT:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, LoginFragment.newInstance());
                break;
            case PERSONAL_INFO_FRAGMENT_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, PersonalInfoFragment.newInstance());
                break;
            case PHOTO_SELECT_FRAGMENT_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, PhotoSelectFragment.newInstance());
                break;
            case SET_PERSONAL_INFO_FRAGMENT_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, SetPersonalInfoFragment.newInstance(getIntent().getBundleExtra(EXTRA_FLAG)));
                break;
            case RESET_PASSWORD_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, ResetPasswordFragment.newInstance());
                break;
            case HISTORY_TODAY_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, HistoryTodayFragment.newInstance());
                break;
            case ENTERTAINMENT_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, EntertainmentMoreListFragment.newInstance());
                break;
            case HEALTH_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, HealthFragment.newInstance());
                break;
            case HEALTH_SEARCH_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, HealthSearchFragment.newInstance());
                break;
            case HEALTH_SEARCH_RESULT_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, HealthSearchResultFragment.newInstance(getIntent().getBundleExtra(EXTRA_FLAG)));
                break;
            case FOOD_FLAG:
                fragmentTransaction.replace(R.id.id_fl_activity_fragment_container, FoodFragment.newInstance());
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}
