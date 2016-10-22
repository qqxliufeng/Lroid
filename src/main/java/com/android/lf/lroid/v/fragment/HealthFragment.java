package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.EntertainmentBean;
import com.android.lf.lroid.m.bean.HealthBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.v.adapter.HealthAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
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
//            if (null != res && res.size() > 0) {
//                ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) res.get("list");
//                ArrayList<HealthBean> tempList = new ArrayList<>();
//                for (HashMap<String, Object> map : list) {
//                    HealthBean healthBean = new HealthBean();
//                    healthBean.setArticleId((String) map.get("id"));
//                    healthBean.setSourceUrl((String) map.get("sourceUrl"));
//                    healthBean.setArticleTitle((String) map.get("title"));
//                    healthBean.setTime((String) map.get("pubTime"));
//                    tempList.add(healthBean);
//                }
                mBaseQuickAdapter.addData(mMobApiPresenter.<HealthBean> parseResult((Map<String, Object>) result));
//            }
        }
    }

    @Override
    protected void onLoadMore() {
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN, "3", Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }
}
