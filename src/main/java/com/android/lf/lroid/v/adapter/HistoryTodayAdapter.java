package com.android.lf.lroid.v.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.HistoryTodayBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by feng on 2016/10/8.
 */

public class HistoryTodayAdapter extends LroidBaseRecycleViewAdapter<HistoryTodayBean> {

    public HistoryTodayAdapter(int layoutResId, List<HistoryTodayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HistoryTodayBean historyTodayBean) {
        baseViewHolder.setText(R.id.id_tv_adapter_history_today_item_title, historyTodayBean.getTitle());
        baseViewHolder.setText(R.id.id_tv_adapter_history_today_item_content, historyTodayBean.getContent());
        baseViewHolder.setText(R.id.id_tv_adapter_history_today_item_time, getDateFormat(historyTodayBean.getTime()));
        baseViewHolder.addOnClickListener(R.id.id_tv_adapter_history_today_item_all);
        TextView tv_content = baseViewHolder.getView(R.id.id_tv_adapter_history_today_item_content);
        TextView tv_all = baseViewHolder.getView(R.id.id_tv_adapter_history_today_item_all);
        if (historyTodayBean.isAll()) {
            tv_content.setMaxLines(Integer.MAX_VALUE);
            tv_all.setText("收起");
        } else {
            tv_content.setMaxLines(2);
            tv_all.setText("全文");
        }
        int position = baseViewHolder.getLayoutPosition();
        ImageView iv_icon = baseViewHolder.getView(R.id.id_iv_adapter_history_today_item_pic);
        if (position % 2 == 0) {
            iv_icon.setColorFilter(Color.RED);
        } else {
            iv_icon.setColorFilter(R.color.colorPrimary);
        }
    }

    public String getDateFormat(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "-" + month + "-" + day;
    }
}
