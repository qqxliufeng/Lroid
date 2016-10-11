package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.EntertainmentBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.v.adapter.MoreContentTwoAdapter;
import com.android.lf.lroid.v.views.LroidListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/10/11.
 */

public class MoreContentTwoFragment extends LroidBaseFragment {

    public static MoreContentTwoFragment newInstance() {
        return new MoreContentTwoFragment();
    }

    @BindView(R.id.id_llv_fragment_more_content_two)
    LroidListView mLroidListView;

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
        adapter = new MoreContentTwoAdapter(mContext, mArrayList, R.layout.adapter_more_content_two_item_layout);
        mLroidListView.setAdapter(adapter);
        mobApiPresenter.setFragment(this);
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_WEIXIN_FOR_ENTERTAINMENT, "11", "1", "20");
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        mProgressDialog = ProgressDialog.show(mContext,null,"正在加载……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        mProgressDialog.dismiss();
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result!=null) {
            HashMap<String, Object> res = (HashMap<String, Object>) ((Map<String, Object>) result).get("result");
            if (null != res && res.size() > 0) {
                ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String, Object>>) res.get("list");
                for (HashMap<String,Object> map :list) {
                    EntertainmentBean entertainmentBean = new EntertainmentBean();
                    entertainmentBean.setId((String) map.get("id"));
                    entertainmentBean.setSourceUrl((String) map.get("sourceUrl"));
                    entertainmentBean.setTitle((String) map.get("title"));
                    entertainmentBean.setTime((String) map.get("pubTime"));
                    mArrayList.add(entertainmentBean);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
