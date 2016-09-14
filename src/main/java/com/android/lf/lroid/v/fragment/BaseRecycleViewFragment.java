package com.android.lf.lroid.v.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.lf.lroid.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class BaseRecycleViewFragment<T> extends LroidBaseFragment {

    @BindView(R.id.id_bt_fragment_base_recycle_view_empty)
    Button mEmptyView;
    @BindView(R.id.id_pb_fragment_base_recycle_view_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.id_rv_fragment_base_recycle_view_content)
    RecyclerView mRecyclerView;

    protected ArrayList<T> mArrayList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_recycle_view_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void setComponent() {

    }
}
