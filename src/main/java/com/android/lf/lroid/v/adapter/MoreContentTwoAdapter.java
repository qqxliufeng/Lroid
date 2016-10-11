package com.android.lf.lroid.v.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.EntertainmentBean;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 2016/10/11.
 */

public class MoreContentTwoAdapter extends LroidBaseListViewAdapter<EntertainmentBean> {

    public MoreContentTwoAdapter(Context context, List<EntertainmentBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, EntertainmentBean item, int position) {
        TextView tv_title = helper.getView(R.id.id_tv_adapter_more_content_two_item_title);
        TextView tv_url = helper.getView(R.id.id_tv_adapter_more_content_two_item_source_url);
        TextView tv_time = helper.getView(R.id.id_tv_adapter_more_content_two_item_time);
        NetworkImageView iv_pic = helper.getView(R.id.id_niv_adapter_more_content_two_item_pic);
        tv_title.setText(item.getTitle());
        tv_url.setText(item.getSourceUrl());
        tv_time.setText(item.getTime());
        iv_pic.setDefaultImageResId(R.drawable.drawable_image_request_default);
        iv_pic.setErrorImageResId(R.drawable.drawable_image_request_default);
        iv_pic.setImageUrl("http://cdn.duitang.com/uploads/item/201601/29/20160129180553_Y3uMf.jpeg", RequestManager.getImageLoader());
    }
}
