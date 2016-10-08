package com.android.lf.lroid.v.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by feng on 2016/9/14.
 */

public abstract class LroidBaseRecycleViewAdapter<T> extends BaseQuickAdapter<T> {

    public LroidBaseRecycleViewAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public LroidBaseRecycleViewAdapter(List<T> data) {
        super(data);
    }

    public LroidBaseRecycleViewAdapter(View contentView, List<T> data) {
        super(contentView, data);
    }

}
