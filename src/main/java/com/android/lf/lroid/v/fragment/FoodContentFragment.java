package com.android.lf.lroid.v.fragment;

import com.android.lf.lroid.v.adapter.FoodContentAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodContentFragment extends BaseRecycleViewFragment {

    public static FoodContentFragment newInstance() {
        return new FoodContentFragment();
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return new FoodContentAdapter(android.R.layout.simple_list_item_1,mArrayList);
    }

    @Override
    protected void setComponent() {

    }
}
