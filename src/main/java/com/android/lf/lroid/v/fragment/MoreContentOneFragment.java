package com.android.lf.lroid.v.fragment;

import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.views.CustomDialog;

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
        MethodUtils.startFragmentsActivity(mContext,"健康", FragmentContainerActivity.HEALTH_FLAG);
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_food)
    public void onFood() {
        MethodUtils.startFragmentsActivity(mContext,"美食", FragmentContainerActivity.FOOD_FLAG);
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_search)
    public void onSearch() {
        CustomDialog.createEmptyDialog(mContext);
    }

    @OnClick(R.id.id_tv_fragment_more_content_one_more)
    public void onMore() {
    }
}
