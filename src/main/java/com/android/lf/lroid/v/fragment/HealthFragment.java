package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.HealthBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.adapter.HealthAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


/**
 * Created by feng on 2016/10/21.
 */

public class HealthFragment extends BaseRecycleViewFragment<HealthBean>{

    public static HealthFragment newInstance() {
        return new HealthFragment();
    }

    @Inject
    MobApiPresenter mMobApiPresenter;

    @Override
    protected BaseQuickAdapter<HealthBean> createAdapter() {
        return new HealthAdapter(R.layout.adapter_health_item_layout,mArrayList);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    protected void initView(View view) {
        mMobApiPresenter.setFragment(this);
        super.initView(view);
        View topView = View.inflate(mContext,R.layout.inner_fragment_health_top_layout,null);
        topView.findViewById(R.id.id_ll_fragment_health_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodUtils.startFragmentsActivity(mContext,"健康生活", FragmentContainerActivity.HEALTH_SEARCH_FLAG);
            }
        });
        mBaseQuickAdapter.addHeaderView(topView);
    }

    @Override
    public void onRefresh() {
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN, "3", Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result != null) {
            HashMap<String, Object> res = (HashMap<String, Object>) ((Map<String, Object>) result).get("result");
                mBaseQuickAdapter.addData(mMobApiPresenter.<HealthBean> parseResult((Map<String, Object>) result));
//            }
        }
    }

    @Override
    protected void onLoadMore() {
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN, "3", Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }

    @Override
    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Bundle bundle = new Bundle();
        HealthBean healthBean = mArrayList.get(i);
        bundle.putString(WebContentFragment.WEB_LOAD_URL, healthBean.getSourceUrl());
        MethodUtils.startFragmentsActivity(mContext, healthBean.getArticleTitle(),FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG,bundle);
    }
}
