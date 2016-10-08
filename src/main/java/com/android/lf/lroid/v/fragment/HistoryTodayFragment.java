package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.HistoryTodayBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.adapter.HistoryTodayAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by feng on 2016/10/8.
 */

public class HistoryTodayFragment extends BaseRecycleViewFragment<HistoryTodayBean> {

    @Inject
    MobApiPresenter mMobApiPresenter;

    public static HistoryTodayFragment newInstance() {
        return new HistoryTodayFragment();
    }

    @Override
    protected BaseQuickAdapter<HistoryTodayBean> createAdapter() {
        return new HistoryTodayAdapter(R.layout.adapter_history_today_item_layout, mArrayList);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mSwipeRefreshLayout.setEnabled(false);
        mMobApiPresenter.setFragment(this);
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_HISTORY_TODAY, MethodUtils.getCurrentTime("MMdd"));
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        super.onRequestSuccess(requestID,result);
        if (result!=null){
            String code = (String) ((Map)result).get("retCode");
            if ("200".equals(code)){
                ArrayList<Map<String,String>> results = (ArrayList<Map<String,String>>) ((Map) result).get("result");
                for (Map<String,String> mapBean: results) {
                    HistoryTodayBean historyTodayBean = new HistoryTodayBean();
                    historyTodayBean.setTime(mapBean.get("date"));
                    historyTodayBean.setContent(mapBean.get("event"));
                    historyTodayBean.setTitle(mapBean.get("title"));
                    mArrayList.add(historyTodayBean);
                }
                mBaseQuickAdapter.notifyDataSetChanged();
            }
        }
    }
}
