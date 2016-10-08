package com.android.lf.lroid.v.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.views.DividerItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public abstract class BaseRecycleViewFragment<T> extends LroidBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.id_bt_fragment_base_recycle_view_empty)
    Button mEmptyView;
    @BindView(R.id.id_pb_fragment_base_recycle_view_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.id_rv_fragment_base_recycle_view_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_srl_fragment_base_recycle_view_content)
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected BaseQuickAdapter<T> mBaseQuickAdapter;

    protected ArrayList<T> mArrayList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_recycle_view_layout;
    }

    @Override
    protected void initView(View view) {
        mBaseQuickAdapter = createAdapter();
        if (mBaseQuickAdapter == null){
            throw new NullPointerException("adapter must be not null");
        }
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.setAdapter(mBaseQuickAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST));
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext,android.R.color.holo_blue_dark),ContextCompat.getColor(mContext,android.R.color.holo_green_dark),ContextCompat.getColor(mContext,android.R.color.holo_orange_dark));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    protected abstract BaseQuickAdapter<T> createAdapter();

    protected RecyclerView.LayoutManager createLayoutManager(){
        return new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }


    @Override
    public void onRequestStart(int requestID) {
        mEmptyView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {
        mProgressBar.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        mProgressBar.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
    }
}
