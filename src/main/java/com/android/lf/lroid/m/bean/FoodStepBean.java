package com.android.lf.lroid.m.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodStepBean implements Parcelable {
    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.step);
    }

    public FoodStepBean() {
    }

    protected FoodStepBean(Parcel in) {
        this.img = in.readString();
        this.step = in.readString();
    }

    public static final Parcelable.Creator<FoodStepBean> CREATOR = new Parcelable.Creator<FoodStepBean>() {
        @Override
        public FoodStepBean createFromParcel(Parcel source) {
            return new FoodStepBean(source);
        }

        @Override
        public FoodStepBean[] newArray(int size) {
            return new FoodStepBean[size];
        }
    };
}