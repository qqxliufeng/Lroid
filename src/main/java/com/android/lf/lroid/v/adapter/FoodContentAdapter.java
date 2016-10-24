package com.android.lf.lroid.v.adapter;

import com.android.lf.lroid.m.bean.FoodInfoBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodContentAdapter extends LroidBaseRecycleViewAdapter<FoodInfoBean>{
    public FoodContentAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FoodInfoBean o) {

    }
}
