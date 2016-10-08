package com.android.lf.lroid.v.adapter;

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
        baseViewHolder.setText(R.id.id_tv_adapter_history_today_item_title,historyTodayBean.getTitle());
        baseViewHolder.setText(R.id.id_tv_adapter_history_today_item_content,historyTodayBean.getContent());
        baseViewHolder.setText(R.id.id_tv_adapter_history_today_item_time,getDateFormat(historyTodayBean.getTime()));
    }

    private String getDateFormat(String date) {
        String year  = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day   = date.substring(6, 8);
        return year + "-" + month + "-" + day;
    }
}
