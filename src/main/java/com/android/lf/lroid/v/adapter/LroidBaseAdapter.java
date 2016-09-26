package com.android.lf.lroid.v.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by feng on 2016/9/14.
 */

public class LroidBaseAdapter extends BaseQuickAdapter {

    public LroidBaseAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public LroidBaseAdapter(List data) {
        super(data);
    }

    public LroidBaseAdapter(View contentView, List data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {
    }
}
