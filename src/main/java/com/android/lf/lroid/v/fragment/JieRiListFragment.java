package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.JieRiBean;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.p.JieRiDataProvidePresenter;
import com.android.lf.lroid.v.views.DividerItemDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/7.
 */

public class JieRiListFragment extends LroidBaseFragment {

    @Inject
    JieRiDataProvidePresenter jieRiDataProvidePresenter;

    private ProgressDialog progressDialog;


    public static JieRiListFragment newInstance(String selectArg) {
        Bundle bundle = new Bundle();
        bundle.putString("selectArg", selectArg);
        JieRiListFragment jieRiListFragment = new JieRiListFragment();
        jieRiListFragment.setArguments(bundle);
        return jieRiListFragment;
    }

    @BindView(R.id.id_rv_fragment_list)
    RecyclerView mRecycleView;
    @BindView(R.id.id_bt_fragment_list_empty)
    Button mEmptyView;

    ArrayList<JieRiBean> mArrayList = new ArrayList<JieRiBean>();

    private MyListViewAdapter myListViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jie_ri_list_layout;
    }

    @Override
    protected void initView(View view) {
        mEmptyView.setVisibility(View.GONE);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        myListViewAdapter = new MyListViewAdapter();
        mRecycleView.setAdapter(myListViewAdapter);
        jieRiDataProvidePresenter.setFragment(this);
        jieRiDataProvidePresenter.getDataFromDB(DataProvider.FEAST_URI, getArguments().getString("selectArg"));
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result != null) {
            if (!((ArrayList<JieRiBean>) result).isEmpty()) {
                mArrayList.addAll((ArrayList<JieRiBean>) result);
                myListViewAdapter.notifyDataSetChanged();
            } else {
                mEmptyView.setVisibility(View.VISIBLE);
            }
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    class MyListViewAdapter extends RecyclerView.Adapter<MyListViewHolder> {

        @Override
        public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyListViewHolder(View.inflate(mContext, android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(MyListViewHolder holder, int position) {
            JieRiBean jieRiBean = mArrayList.get(position);
            holder.tv_content.setText(jieRiBean.getTime() + "        " + jieRiBean.getName());
        }

        @Override
        public int getItemCount() {
            return mArrayList.size();
        }
    }

    class MyListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_content;

        public MyListViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }


}
