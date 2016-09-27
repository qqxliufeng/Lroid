package com.android.lf.lroid.m.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PhotoFolderBean implements Serializable {

    private String name;
    private String dirPath;
    private ArrayList<PhotoBean> photoList;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public List<PhotoBean> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<PhotoBean> photoList) {
        this.photoList = photoList;
    }
}
