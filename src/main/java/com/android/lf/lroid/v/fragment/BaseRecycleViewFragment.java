package com.android.lf.lroid.v.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.views.DividerItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public abstract class BaseRecycleViewFragment<T> extends LroidBaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.id_bt_fragment_base_recycle_view_empty)
    Button mEmptyView;
    @BindView(R.id.id_rv_fragment_base_recycle_view_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_srl_fragment_base_recycle_view_content)
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected BaseQuickAdapter<T> mBaseQuickAdapter;
    protected ArrayList<T> mArrayList = new ArrayList<>();

    protected int MAX_PAGE_COUNT = 5;
    protected int PAGE_SIZE = 10;
    protected int current_page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_recycle_view_layout;
    }

    @Override
    protected void initView(View view) {
        mBaseQuickAdapter = createAdapter();
        if (mBaseQuickAdapter == null) {
            throw new NullPointerException("adapter must be not null");
        }
        mBaseQuickAdapter.openLoadAnimation();
        mBaseQuickAdapter.openLoadMore(PAGE_SIZE);
        mBaseQuickAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mBaseQuickAdapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, android.R.color.holo_blue_dark), ContextCompat.getColor(mContext, android.R.color.holo_green_dark), ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                onSimpleItemClick(baseQuickAdapter, view, i);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                onItemViewChildClick(adapter, view, position);
            }
        });
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        onRefresh();
    }

    protected abstract BaseQuickAdapter<T> createAdapter();

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onRequestStart(int requestID) {
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {
        Logger.e(e.getMessage(), "error");
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void onItemViewChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onLoadMoreRequested() {
        if (current_page >= MAX_PAGE_COUNT) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mBaseQuickAdapter.loadComplete();
                }
            });
        } else {
            current_page++;
            onLoadMore();
        }
    }

    /**
     * 子类若需下拉刷新功能，则直接重写方法
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
    }

    /**
     * 子类若需上拉加载功能，则直接重写方法
     * 上拉加载
     */
    protected void onLoadMore() {
    }

}
