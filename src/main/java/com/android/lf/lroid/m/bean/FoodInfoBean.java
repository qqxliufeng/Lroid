package com.android.lf.lroid.m.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by feng on 2016/10/24.
 */

public class FoodInfoBean implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.recipe, flags);
        dest.writeString(this.name);
        dest.writeString(this.ctgTitles);
        dest.writeString(this.menuId);
        dest.writeString(this.thumbnail);
        dest.writeStringList(this.ctgIds);
    }

    public FoodInfoBean() {
    }

    protected FoodInfoBean(Parcel in) {
        this.recipe = in.readParcelable(FoodRecipeBean.class.getClassLoader());
        this.name = in.readString();
        this.ctgTitles = in.readString();
        this.menuId = in.readString();
        this.thumbnail = in.readString();
        this.ctgIds = in.createStringArrayList();
    }

    public static final Parcelable.Creator<FoodInfoBean> CREATOR = new Parcelable.Creator<FoodInfoBean>() {
        @Override
        public FoodInfoBean createFromParcel(Parcel source) {
            return new FoodInfoBean(source);
        }

        @Override
        public FoodInfoBean[] newArray(int size) {
            return new FoodInfoBean[size];
        }
    };
}
