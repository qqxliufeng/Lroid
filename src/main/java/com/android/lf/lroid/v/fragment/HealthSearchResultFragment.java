package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.HealthSearchResultBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.adapter.HealthSearchResultAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by liufeng on 16/10/22.
 */

public class HealthSearchResultFragment extends BaseRecycleViewFragment<HealthSearchResultBean> {

    public static final String KEYWORD = "keyword";

    public static HealthSearchResultFragment newInstance(Bundle args) {
        HealthSearchResultFragment fragment = new HealthSearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    MobApiPresenter mobApiPresenter;

    @Override
    protected BaseQuickAdapter<HealthSearchResultBean> createAdapter() {
        return new HealthSearchResultAdapter(R.layout.adapter_health_search_result_item_layout,mArrayList);
    }

    @Override
    protected void initView(View view) {
        mobApiPresenter.setFragment(this);
        super.initView(view);
        mSwipeRefreshLayout.setEnabled(false);
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_HEALTH,getArguments().getString(KEYWORD),"1","20");
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        super.onRequestSuccess(requestID, result);
        if (result!=null){
            HashMap<String, Object> res = (HashMap<String, Object>) ((Map<String, Object>)result).get("result");
            ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) res.get("list");
            if (list!=null){
                ArrayList<HealthSearchResultBean> tempList = new ArrayList<>();
                for (HashMap<String, Object> map : list) {
                    HealthSearchResultBean healthBean = new HealthSearchResultBean();
                    healthBean.setTitle((String) map.get("title"));
                    healthBean.setContent((String) map.get("content"));
                    tempList.add(healthBean);
                }
                mBaseQuickAdapter.addData(tempList);
                mBaseQuickAdapter.loadComplete();
            }else {
                Toast.makeText(mContext, "暂无搜索结果", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(mContext, "暂无搜索结果", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {
        super.onRequestFail(requestID, e);
        Toast.makeText(mContext, "暂无搜索结果", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Bundle bundle = new Bundle();
        HealthSearchResultBean healthSearchResultBean = mArrayList.get(i);
        bundle.putString(WebContentFragment.WEB_LOAD_URL,htmlFormat(healthSearchResultBean));
        MethodUtils.startFragmentsActivity(mContext, healthSearchResultBean.getTitle(), FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG,bundle);
    }


    private String htmlFormat(HealthSearchResultBean bean) {
        return "<h2 align=\"center\">" + bean.getTitle() + "</h2>" +
                "<p align=\"center\">" + bean.getContent() + "</p>";
    }
}
