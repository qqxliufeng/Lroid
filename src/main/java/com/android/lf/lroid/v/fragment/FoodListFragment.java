package com.android.lf.lroid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.FoodInfoBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.v.activity.FoodContentActivity;
import com.android.lf.lroid.v.adapter.FoodContentAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodListFragment extends BaseRecycleViewFragment<FoodInfoBean> {

    public static final String CID = "cid";

    public static FoodListFragment newInstance(String cid) {
        Bundle args = new Bundle();
        args.putString(CID, cid);
        FoodListFragment fragment = new FoodListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    MobApiPresenter mobApiPresenter;

    @Override
    protected BaseQuickAdapter<FoodInfoBean> createAdapter() {
        return new FoodContentAdapter(R.layout.adapter_food_content_item_layout, mArrayList);
    }

    @Override
    protected void initView(View view) {
        mobApiPresenter.setFragment(this);
        super.initView(view);
        mSwipeRefreshLayout.setEnabled(false);
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_FOOD_FOR_INFO, getArguments().getString(CID), null, Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }


    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result != null) {
            switch (requestID) {
                case 0x1:
                    HashMap<String, Object> resultMap = (HashMap<String, Object>) ((HashMap<String, Object>) result).get("result");
                    current_page = (int) resultMap.get("curPage");
                    MAX_PAGE_COUNT = (int) resultMap.get("total") / PAGE_SIZE;
                    if (current_page >= MAX_PAGE_COUNT) {
                        mBaseQuickAdapter.loadComplete();
                    } else {
                        current_page++;
                    }
                    mobApiPresenter.doSomethingWithRxJavaMap(MobApiPresenter.PARSE_FOOD_LIST_FLAG, result);
                    break;
                case MobApiPresenter.PARSE_FOOD_LIST_FLAG:
                    mBaseQuickAdapter.addData((ArrayList<FoodInfoBean>) result);
                    break;
            }
        }
    }

    @Override
    protected void onLoadMore() {
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_FOOD_FOR_INFO, getArguments().getString(CID), null, Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }

    @Override
    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent = new Intent(mContext, FoodContentActivity.class);
        intent.putExtra(FoodContentActivity.FOOD_INFO_FLAG,mArrayList.get(i));
        startActivity(intent);
    }
}
