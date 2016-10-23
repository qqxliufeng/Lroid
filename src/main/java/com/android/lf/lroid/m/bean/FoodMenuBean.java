package com.android.lf.lroid.m.bean;

import java.util.ArrayList;

/**
 * Created by liufeng on 16/10/23.
 */

public class FoodMenuBean {

    private String name;
    private String ctgId;
    private String parentId;
    private ArrayList<FoodMenuBean> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtgId() {
        return ctgId;
    }

    public void setCtgId(String ctgId) {
        this.ctgId = ctgId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ArrayList<FoodMenuBean> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<FoodMenuBean> childs) {
        this.childs = childs;
    }
}
