package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.FoodInfoBean;
import com.android.lf.lroid.m.bean.FoodRecipeBean;
import com.android.lf.lroid.m.bean.FoodStepBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.v.adapter.FoodContentAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodContentFragment extends BaseRecycleViewFragment<FoodInfoBean> {

    public static final String CID = "cid";

    public static FoodContentFragment newInstance(String cid) {
        Bundle args = new Bundle();
        args.putString(CID, cid);
        FoodContentFragment fragment = new FoodContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    MobApiPresenter mobApiPresenter;

    @Override
    protected BaseQuickAdapter<FoodInfoBean> createAdapter() {
        return new FoodContentAdapter(R.layout.adapter_food_content_item_layout, mArrayList);
    }

    @Override
    protected void initView(View view) {
        mobApiPresenter.setFragment(this);
        super.initView(view);
//        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_FOOD_FOR_INFO, getArguments().getString(CID), null, Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }


    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        try {
            if (result != null) {
                HashMap<String, Object> resultMap = (HashMap<String, Object>) ((HashMap<String, Object>) result).get("result");
                if (resultMap != null && !resultMap.isEmpty()) {
                    ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) resultMap.get("list");
                    ArrayList<FoodInfoBean> tempList = new ArrayList<>();
                    if (list != null && !list.isEmpty()) {
                        for (HashMap<String, Object> listMap : list) {
                            FoodInfoBean foodInfoBean = new FoodInfoBean();

                            foodInfoBean.setCtgTitles((String) listMap.get("ctgTitles"));
                            foodInfoBean.setMenuId((String) listMap.get("menuId"));
                            foodInfoBean.setThumbnail((String) listMap.get("thumbnail"));
                            foodInfoBean.setName((String) listMap.get("name"));
                            ArrayList<String> ctgIdsList = (ArrayList<String>) listMap.get("ctgIds");
                            foodInfoBean.setCtgIds(ctgIdsList);

                            HashMap<String, Object> recipeMap = (HashMap<String, Object>) listMap.get("recipe");
                            FoodRecipeBean recipeBean = new FoodRecipeBean();
                            recipeBean.setImg((String) recipeMap.get("img"));
                            recipeBean.setSumary((String) recipeMap.get("sumary"));
                            recipeBean.setTitle((String) recipeMap.get("title"));

                            String methodList = (String) recipeMap.get("method");
                            if (methodList != null) {
                                JSONArray jsonArray = new JSONArray(methodList);
                                ArrayList<FoodStepBean> foodStepList = new ArrayList<>();
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.optJSONObject(i);
                                        FoodStepBean foodStep = new Gson().fromJson(object.toString(), FoodStepBean.class);
                                        foodStepList.add(foodStep);
                                    }
                                    recipeBean.setMethod(foodStepList);
                                }
                            }
                            String ingredients = (String) recipeMap.get("ingredients");
                            recipeBean.setIngredients(ingredients);

                            foodInfoBean.setRecipe(recipeBean);
                            tempList.add(foodInfoBean);
                        }
                        mBaseQuickAdapter.addData(tempList);
                        current_page = (int) resultMap.get("curPage");
                        MAX_PAGE_COUNT = (int) resultMap.get("total") / PAGE_SIZE;
                        if (current_page >= MAX_PAGE_COUNT) {
                            mBaseQuickAdapter.loadComplete();
                        } else {
                            current_page++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mBaseQuickAdapter.loadComplete();
        }
    }

    @Override
    protected void onLoadMore() {
        mobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_FOOD_FOR_INFO, getArguments().getString(CID), null, Integer.toString(current_page), Integer.toString(PAGE_SIZE));
    }
}
