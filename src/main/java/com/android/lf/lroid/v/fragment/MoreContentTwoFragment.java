package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.EntertainmentBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.adapter.MoreContentTwoAdapter;
import com.android.lf.lroid.v.views.LroidListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by feng on 2016/10/11.
 */

public class MoreContentTwoFragment extends LroidBaseFragment {

    public static MoreContentTwoFragment newInstance() {
        return new MoreContentTwoFragment();
    }

    @BindView(R.id.id_llv_fragment_more_content_two)
    LroidListView mLroidListView;
    @BindView(R.id.id_bt_fragment_more_content_two_empty)
    Button mEmptyView;

    private ArrayList<EntertainmentBean> mArrayList = new ArrayList<>();
    private MoreContentTwoAdapter adapter;

    @Inject
    MobApiPresenter mobApiPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_content_two_layout;
    }

    @Override
    protected void initView(View view) {
        View footView = View.inflate(mContext, R.layout.foot_more_content_two_layout, null);
        mLroidListView.addFooterView(footView);
        adapter = new MoreContentTwoAdapter(mContext, mArrayList, R.layout.adapter_entertainment_more_item_layout);
        mLroidListView.setAdapter(adapter);
        mLroidListView.setVisibility(View.INVISIBLE);
        mobApiPresenter.setFragment(this);
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN, "11", "1", "20");
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        mProgressDialog = ProgressDialog.show(mContext, null, "正在加载……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        mProgressDialog.dismiss();
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        mLroidListView.setVisibility(View.VISIBLE);
        if (result != null) {
            HashMap<String, Object> res = (HashMap<String, Object>) ((Map<String, Object>) result).get("result");
            if (null != res && res.size() > 0) {
                ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) res.get("list");
                for (HashMap<String, Object> map : list) {
                    EntertainmentBean entertainmentBean = new EntertainmentBean();
                    entertainmentBean.setId((String) map.get("id"));
                    entertainmentBean.setSourceUrl((String) map.get("sourceUrl"));
                    entertainmentBean.setTitle((String) map.get("title"));
                    entertainmentBean.setTime((String) map.get("pubTime"));
                    mArrayList.add(entertainmentBean);
                }
                adapter.notifyDataSetChanged();
            }else {
                mEmptyView.setVisibility(View.VISIBLE);
            }
        }else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {
        super.onRequestFail(requestID, e);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @OnItemClick(R.id.id_llv_fragment_more_content_two)
    public void onItemClick(int position) {
        if (position == mArrayList.size()) {
            MethodUtils.startFragmentsActivity(mContext, "娱乐", FragmentContainerActivity.ENTERTAINMENT_FLAG);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(WebContentFragment.WEB_LOAD_URL, mArrayList.get(position).getSourceUrl());
            MethodUtils.startFragmentsActivity(mContext, mArrayList.get(position).getTitle(), FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG, bundle);
        }
    }

}
