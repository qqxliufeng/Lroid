package com.android.lf.lroid.m.bean;

import java.util.ArrayList;

/**
 * Created by feng on 2016/10/24.
 */

public class FoodInfoBean {

    private FoodRecipeBean recipe;
    private String name;
    private String ctgTitles;
    private String menuId;
    private String thumbnail;
    private ArrayList<String> ctgIds;

    public FoodRecipeBean getRecipe() {
        return recipe;
    }

    public void setRecipe(FoodRecipeBean recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtgTitles() {
        return ctgTitles;
    }

    public void setCtgTitles(String ctgTitles) {
        this.ctgTitles = ctgTitles;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<String> getCtgIds() {
        return ctgIds;
    }

    public void setCtgIds(ArrayList<String> ctgIds) {
        this.ctgIds = ctgIds;
    }

}
