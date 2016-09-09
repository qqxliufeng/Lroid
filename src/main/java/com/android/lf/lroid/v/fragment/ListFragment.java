package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lf.lroid.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/7.
 */

public class ListFragment extends BaseFragment {

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @BindView(R.id.id_rv_fragment_list)
    RecyclerView mRecycleView;

    ArrayList<String> mArrayList = new ArrayList<String>(){
        {
            for (int i = 0;i<50;i++){
                add("item is --> " + i);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    protected void initView(View view) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setAdapter(new MyListViewAdapter());
    }

    @Override
    protected void setComponent() {
    }

    class MyListViewAdapter extends RecyclerView.Adapter<MyListViewHolder>{

        @Override
        public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyListViewHolder(View.inflate(mContext,android.R.layout.simple_list_item_1,null));
        }

        @Override
        public void onBindViewHolder(MyListViewHolder holder, int position) {
            holder.tv_content.setText(mArrayList.get(position));
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
