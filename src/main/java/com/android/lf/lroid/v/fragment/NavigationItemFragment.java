package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.activity.SplashActivity;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class NavigationItemFragment extends LroidBaseFragment {

    public static NavigationItemFragment newInstance(int resId,boolean isShowButton) {
        Bundle args = new Bundle();
        args.putInt("img_res",resId);
        args.putBoolean("is_show_button",isShowButton);
        NavigationItemFragment fragment = new NavigationItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.id_iv_fragment_navigation_item_image)
    ImageView mImageView;
    @BindView(R.id.id_bt_fragment_navigation_item_enter)
    Button mButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_item_layout;
    }

    @Override
    protected void initView(View view) {
        mImageView.setImageResource(getArguments().getInt("img_res"));
        if (getArguments().getBoolean("is_show_button")){
            mButton.setVisibility(View.VISIBLE);
            mButton.setOnClickListener(this);
        }else {
            mButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_bt_fragment_navigation_item_enter){
            PreferenceUtils.setPrefBoolean(mContext, SplashActivity.IS_NAVIGATION_FLAG,false);
            startActivity(new Intent(mContext, HomeActivity.class));
            finishActivity();
        }
    }
}
