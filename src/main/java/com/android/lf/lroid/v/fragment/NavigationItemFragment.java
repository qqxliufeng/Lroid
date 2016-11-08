package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.activity.SplashActivity;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class NavigationItemFragment extends LroidBaseFragment {

    public static final String TEXT_RES = "text_res";
    public static final String BG_COLOR = "bg_color";
    public static final String SHOW_BUTTON = "is_show_button";

    public static NavigationItemFragment newInstance(String resId, int bgColor,boolean isShowButton) {
        Bundle args = new Bundle();
        args.putString(TEXT_RES, resId);
        args.putBoolean(SHOW_BUTTON, isShowButton);
        args.putInt(BG_COLOR, bgColor);
        NavigationItemFragment fragment = new NavigationItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.id_tv_fragment_navigation_item)
    TextView mTextView;
    @BindView(R.id.id_bt_fragment_navigation_item_enter)
    TextView mButton;
    @BindView(R.id.id_fl_fragment_navigation_item_container)
    FrameLayout mFrameLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_item_layout;
    }

    @Override
    protected void initView(View view) {
        mFrameLayout.setBackgroundColor(ContextCompat.getColor(mContext,getArguments().getInt(BG_COLOR,R.color.colorPrimary)));
        mTextView.setText(getArguments().getString(TEXT_RES));
        if (getArguments().getBoolean(SHOW_BUTTON)) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setOnClickListener(this);
        } else {
            mButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setComponent() {}

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_bt_fragment_navigation_item_enter) {
            PreferenceUtils.setPrefBoolean(mContext, SplashActivity.IS_NAVIGATION_FLAG, false);
            startActivity(new Intent(mContext, HomeActivity.class));
            finishActivity();
        }
    }
}
