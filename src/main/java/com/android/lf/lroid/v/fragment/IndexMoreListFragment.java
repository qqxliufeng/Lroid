package com.android.lf.lroid.v.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.JieRiBean;
import com.android.lf.lroid.m.data.JieRiData;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.p.common.JieRiDataProvidePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/7.
 */

public class IndexMoreListFragment extends LroidBaseFragment {

    @Inject
    JieRiDataProvidePresenter jieRiDataProvidePresenter;

    public static IndexMoreListFragment newInstance() {
        return new IndexMoreListFragment();
    }

    @BindView(R.id.id_rv_fragment_list)
    RecyclerView mRecycleView;

    ArrayList<JieRiBean> mArrayList = new ArrayList<JieRiBean>();

    private MyListViewAdapter myListViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    protected void initView(View view) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        myListViewAdapter = new MyListViewAdapter();
        mRecycleView.setAdapter(myListViewAdapter);
        jieRiDataProvidePresenter.setFragment(this);
        jieRiDataProvidePresenter.getDataFromDB(DataProvider.FEAST_URI);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        super.onRequestStart(requestID);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        mArrayList.addAll((ArrayList<JieRiBean>)result);
        myListViewAdapter.notifyDataSetChanged();
    }

    class MyListViewAdapter extends RecyclerView.Adapter<MyListViewHolder>{

        @Override
        public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyListViewHolder(View.inflate(mContext,android.R.layout.simple_list_item_1,null));
        }

        @Override
        public void onBindViewHolder(MyListViewHolder holder, int position) {
            holder.tv_content.setText(mArrayList.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mArrayList.size();
        }
    }

    class MyListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_content;

        public MyListViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }


}
