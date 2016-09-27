package com.android.lf.lroid.v.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.PhotoBean;
import com.android.lf.lroid.utils.ImageLoader;
import com.android.lf.lroid.utils.ScreenUtils;

import java.util.List;

/**
 * Created by feng on 2016/9/27.
 */

public class PhotoSelectAdapter extends LroidBaseListViewAdapter<PhotoBean> {

    private int mImageWidth;

    public PhotoSelectAdapter(Context context, List<PhotoBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        mImageWidth = (ScreenUtils.getScreenWidth(context) - (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics())) / 3;
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public PhotoBean getItem(int position) {
        if (position != 0) {
            return mDatas.get(position - 1);
        }
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            convertView = View.inflate(mContext, R.layout.adapter_photo_select_camera_layout, null);
            GridView.LayoutParams lp = new GridView.LayoutParams(mImageWidth, mImageWidth);
            convertView.setLayoutParams(lp);
            convertView.setTag(null);
        } else {
            MyViewHolder myViewHolder = null;
            if (convertView == null) {
                myViewHolder = new MyViewHolder();
                convertView = View.inflate(mContext, R.layout.adapter_photo_select_item_layout, null);
                convertView.setTag(myViewHolder);
                myViewHolder.iv = (ImageView) convertView.findViewById(R.id.id_adapter_photo_select_item_img);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.iv.setImageResource(R.drawable.img_home_mine_default_img_icon);
            ImageLoader.getInstance().display(getItem(position).getPath(), myViewHolder.iv, mImageWidth, mImageWidth);
        }
        return convertView;
    }


    @Override
    public void convert(ViewHolder helper, PhotoBean item, int position) {
    }

    class MyViewHolder {
        ImageView iv;
    }

}
