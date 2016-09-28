package com.android.lf.lroid.v.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.PhotoFolderBean;
import com.android.lf.lroid.utils.ImageLoader;

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
        ImageView iv_img = helper.getView(R.id.id_iv_fragment_photo_select_folder_item_img);
        TextView tv_name = helper.getView(R.id.id_tv_fragment_photo_select_folder_item_name);
        TextView tv_num = helper.getView(R.id.id_tv_fragment_photo_select_folder_item_num);
        ImageView iv_select = helper.getView(R.id.id_iv_fragment_photo_select_folder_item_select);
        iv_img.setImageResource(R.drawable.img_home_mine_default_img_icon);
        tv_name.setText(item.getName());
        tv_num.setText(item.getPhotoList().size()+" 张");
        if (item.isSelected()){
            iv_select.setVisibility(View.VISIBLE);
        }else {
            iv_select.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().display(item.getPhotoList().get(0).getPath(),iv_img,iv_img.getMeasuredWidth(),iv_img.getMeasuredWidth());
    }
}
