package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.EntertainmentBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.adapter.EntertainmentMoreAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by feng on 2016/10/13.
 */

public class EntertainmentMoreListFragment extends BaseRecycleViewFragment<EntertainmentBean> {

    public static EntertainmentMoreListFragment newInstance() {
        return  new EntertainmentMoreListFragment();
    }

    @Inject
    MobApiPresenter mobApiPresenter;

    @Override
    protected BaseQuickAdapter<EntertainmentBean> createAdapter() {
        return new EntertainmentMoreAdapter(R.layout.adapter_entertainment_more_item_layout,mArrayList);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    protected void initView(View view) {
        mobApiPresenter.setFragment(this);
        super.initView(view);
    }

    @Override
    public void onRefresh() {
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN, "11", Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result != null) {
            HashMap<String, Object> res = (HashMap<String, Object>) ((Map<String, Object>) result).get("result");
            if (null != res && res.size() > 0) {
                ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) res.get("list");
                ArrayList<EntertainmentBean> tempList = new ArrayList<>();
                for (HashMap<String, Object> map : list) {
                    EntertainmentBean entertainmentBean = new EntertainmentBean();
                    entertainmentBean.setId((String) map.get("id"));
                    entertainmentBean.setSourceUrl((String) map.get("sourceUrl"));
                    entertainmentBean.setTitle((String) map.get("title"));
                    entertainmentBean.setTime((String) map.get("pubTime"));
                    tempList.add(entertainmentBean);
                }
                mBaseQuickAdapter.addData(tempList);
            }
        }
    }

    @Override
    protected void onLoadMore() {
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN, "11", Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }

    @Override
    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Bundle bundle = new Bundle();
        EntertainmentBean entertainmentBean = mArrayList.get(i);
        bundle.putString(WebContentFragment.WEB_LOAD_URL, entertainmentBean.getSourceUrl());
        MethodUtils.startFragmentsActivity(mContext, entertainmentBean.getTitle(), FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG, bundle);
    }
}
