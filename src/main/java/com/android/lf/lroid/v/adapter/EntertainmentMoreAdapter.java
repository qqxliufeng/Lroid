package com.android.lf.lroid.v.adapter;

import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.EntertainmentBean;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by feng on 2016/10/13.
 */

public class EntertainmentMoreAdapter extends LroidBaseRecycleViewAdapter<EntertainmentBean> {

    public EntertainmentMoreAdapter(int layoutResId, List<EntertainmentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, EntertainmentBean item) {
        TextView tv_title = baseViewHolder.getView(R.id.id_tv_adapter_more_content_two_item_title);
        TextView tv_url = baseViewHolder.getView(R.id.id_tv_adapter_more_content_two_item_source_url);
        TextView tv_time = baseViewHolder.getView(R.id.id_tv_adapter_more_content_two_item_time);
        NetworkImageView iv_pic = baseViewHolder.getView(R.id.id_niv_adapter_more_content_two_item_pic);
        tv_title.setText(item.getTitle());
        tv_url.setText(item.getSourceUrl());
        tv_time.setText(item.getTime());
        iv_pic.setDefaultImageResId(R.drawable.drawable_image_request_default);
        iv_pic.setErrorImageResId(R.drawable.drawable_image_request_default);
        iv_pic.setImageUrl("http://cdn.duitang.com/uploads/item/201601/29/20160129180553_Y3uMf.jpeg", RequestManager.getImageLoader());
    }
}
