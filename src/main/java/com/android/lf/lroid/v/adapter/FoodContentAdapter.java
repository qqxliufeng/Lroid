package com.android.lf.lroid.v.adapter;

import android.text.Html;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.FoodInfoBean;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodContentAdapter extends LroidBaseRecycleViewAdapter<FoodInfoBean>{
    public FoodContentAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FoodInfoBean o) {
        baseViewHolder.setText(R.id.id_tv_adapter_food_content_item_title,o.getName());
        baseViewHolder.setText(R.id.id_tv_adapter_food_content_item_summary,o.getRecipe().getSumary().trim());
        String step = "共分为 "+ o.getRecipe().getMethod().size() +" 步";
        baseViewHolder.setText(R.id.id_tv_adapter_food_content_item_step,step);
        NetworkImageView pic = baseViewHolder.getView(R.id.id_iv_adapter_food_content_item_pic);
        pic.setDefaultImageResId(R.drawable.drawable_image_request_default);
        pic.setErrorImageResId(R.drawable.drawable_image_request_default);
        if (o.getThumbnail()!=null){
            pic.setImageUrl(o.getThumbnail(), RequestManager.getImageLoader());
        }else {
            pic.setImageUrl("http://lroid/temp/temp.jpg", RequestManager.getImageLoader());
        }
    }
}
