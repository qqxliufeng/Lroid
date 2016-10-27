package com.android.lf.lroid.v.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by feng on 2016/10/27.
 */

public class FoodStepView extends LinearLayout {

    private TextView tv_title;
    private NetworkImageView niv_pic;

    public FoodStepView(Context context) {
        super(context);
        initView(context);
    }

    public FoodStepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FoodStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.inner_food_step_item_layout,this,true);
        tv_title = (TextView) findViewById(R.id.id_tv_inner_food_step_title);
        niv_pic = (NetworkImageView) findViewById(R.id.id_niv_inner_food_step_pic);
    }

    public void setNetWorkImageView(String path){
        if (TextUtils.isEmpty(path)){
            niv_pic.setVisibility(GONE);
        }else {
            niv_pic.setDefaultImageResId(R.drawable.drawable_image_request_default);
            niv_pic.setErrorImageResId(R.drawable.drawable_image_request_default);
            niv_pic.setImageUrl(path, RequestManager.getImageLoader());
        }
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

}
