package com.android.lf.lroid.v.adapter;

import com.android.lf.lroid.m.bean.HealthBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by feng on 2016/10/21.
 */

public class HealthAdapter extends LroidBaseRecycleViewAdapter<HealthBean> {

    public HealthAdapter(int layoutResId, List<HealthBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HealthBean healthBean) {

    }
}
