package com.android.lf.lroid.v.adapter;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.HealthSearchResultBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by liufeng on 16/10/22.
 */

public class HealthSearchResultAdapter extends LroidBaseRecycleViewAdapter<HealthSearchResultBean> {

    public HealthSearchResultAdapter(int layoutResId, List<HealthSearchResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HealthSearchResultBean healthSearchResultBean) {
        baseViewHolder.setText(R.id.id_tv_adapter_health_search_result_item_title,healthSearchResultBean.getTitle());
        baseViewHolder.setText(R.id.id_tv_adapter_health_search_result_item_content,healthSearchResultBean.getContent());
    }
}
