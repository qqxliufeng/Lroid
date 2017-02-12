package com.android.lf.lroid.v.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.FoodInfoBean;
import com.android.lf.lroid.m.bean.FoodStepBean;
import com.android.lf.lroid.v.views.FoodStepView;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feng on 2016/10/27.
 */

public class FoodContentActivity extends BaseActivity {

    public static final String FOOD_INFO_FLAG = "food_info_flag";

    @BindView(R.id.id_ctbl_activity_food_content_container)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.id_tl_activity_food_content_bar)
    Toolbar mToolBar;
    @BindView(R.id.id_niv_activity_food_content_pic)
    NetworkImageView mNetWorkImageView;
    @BindView(R.id.id_tv_activity_food_content_summary)
    TextView tv_summary;
    @BindView(R.id.id_tv_activity_food_content_ingredients)
    TextView tv_ingredient;
    @BindView(R.id.id_ll_activity_food_content_stop_container)
    LinearLayout ll_step_container;

    @Override
    public int getLayoutId() {
        return R.layout.activity_food_content_layout;
    }

    @Override
    public void setPresentComponent() {
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            FoodInfoBean foodInfoBean = getIntent().getParcelableExtra(FOOD_INFO_FLAG);
            mCollapsingToolbarLayout.setTitle(foodInfoBean.getName());
            mNetWorkImageView.setDefaultImageResId(R.drawable.drawable_image_request_default);
            mNetWorkImageView.setErrorImageResId(R.drawable.drawable_image_request_default);
            mNetWorkImageView.setImageUrl(TextUtils.isEmpty(foodInfoBean.getThumbnail()) ? "http://lroid/temp.jpg" : foodInfoBean.getThumbnail(), RequestManager.getImageLoader());
            mToolBar.setNavigationIcon(R.drawable.img_app_top_back_icon);
            setSupportActionBar(mToolBar);

            String summary = foodInfoBean.getRecipe().getSumary();
            tv_summary.setText(TextUtils.isEmpty(summary) ? "暂无简介" : "简介:\n" + summary);
            String ingredients = foodInfoBean.getRecipe().getIngredients();
            tv_ingredient.setText(TextUtils.isEmpty(ingredients) ? "暂无配料" : "配料:\n" + ingredients);

            ArrayList<FoodStepBean> steps = foodInfoBean.getRecipe().getMethod();
            if (steps != null && !steps.isEmpty()) {
                for (FoodStepBean stepBean : steps) {
                    FoodStepView foodStepView = new FoodStepView(this);
                    foodStepView.setNetWorkImageView(stepBean.getImg());
                    foodStepView.setTitle(stepBean.getStep());
                    ll_step_container.addView(foodStepView);
                }
            }
        }
    }

}
