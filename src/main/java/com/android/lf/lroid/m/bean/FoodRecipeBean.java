package com.android.lf.lroid.m.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FoodRecipeBean implements Parcelable {
        private String title;
        private ArrayList<FoodStepBean> method;
        private String ingredients;
        private String sumary;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<FoodStepBean> getMethod() {
            return method;
        }

        public void setMethod(ArrayList<FoodStepBean> method) {
            this.method = method;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getSumary() {
            return sumary;
        }

        public void setSumary(String sumary) {
            this.sumary = sumary;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeList(this.method);
        dest.writeString(this.ingredients);
        dest.writeString(this.sumary);
        dest.writeString(this.img);
    }

    public FoodRecipeBean() {
    }

    protected FoodRecipeBean(Parcel in) {
        this.title = in.readString();
        this.method = new ArrayList<FoodStepBean>();
        in.readList(this.method, FoodStepBean.class.getClassLoader());
        this.ingredients = in.readString();
        this.sumary = in.readString();
        this.img = in.readString();
    }

    public static final Parcelable.Creator<FoodRecipeBean> CREATOR = new Parcelable.Creator<FoodRecipeBean>() {
        @Override
        public FoodRecipeBean createFromParcel(Parcel source) {
            return new FoodRecipeBean(source);
        }

        @Override
        public FoodRecipeBean[] newArray(int size) {
            return new FoodRecipeBean[size];
        }
    };
}