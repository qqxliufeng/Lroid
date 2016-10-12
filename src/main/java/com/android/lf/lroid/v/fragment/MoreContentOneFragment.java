package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.lf.lroid.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/10/11.
 */

public class MoreContentOneFragment extends LroidBaseFragment {

    public static MoreContentOneFragment newInstance() {
        return new MoreContentOneFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_content_one_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void setComponent() {
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_health)
    public void onHealth() {
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_food)
    public void onFood() {
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_search)
    public void onSearch() {
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_more)
    public void onMore() {
    }


}
