package com.android.lf.lroid.v.adapter;

import android.content.Context;

import com.android.lf.lroid.m.bean.PhotoFolderBean;

import java.util.List;

/**
 * Created by feng on 2016/9/27.
 */

public class PhotoFolderAdapter extends LroidBaseListViewAdapter<PhotoFolderBean>{

    public PhotoFolderAdapter(Context context, List<PhotoFolderBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, PhotoFolderBean item, int position) {

    }
}
