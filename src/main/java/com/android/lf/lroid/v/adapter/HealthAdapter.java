package com.android.lf.lroid.v.adapter;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.HealthBean;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;
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
        baseViewHolder.setText(R.id.id_tv_adapter_health_item_title,healthBean.getArticleTitle());
        baseViewHolder.setText(R.id.id_tv_adapter_health_item_source_url,healthBean.getSourceUrl());
        baseViewHolder.setText(R.id.id_tv_adapter_health_item_time,healthBean.getTime());
        NetworkImageView networkImageView = baseViewHolder.getView(R.id.id_niv_adapter_health_item_pic);
        networkImageView.setDefaultImageResId(R.drawable.drawable_image_request_default);
        networkImageView.setErrorImageResId(R.drawable.drawable_image_request_default);
        networkImageView.setImageUrl("http://pic.58pic.com/58pic/14/86/04/07S58PICeA9_1024.jpg", RequestManager.getImageLoader());
    }
}
