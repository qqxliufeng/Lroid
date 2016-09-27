package com.android.lf.lroid.v.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.fragment.LoginFragment;
import com.android.lf.lroid.v.fragment.PersonalInfoFragment;
import com.android.lf.lroid.v.fragment.PhotoSelectFragment;
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
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
