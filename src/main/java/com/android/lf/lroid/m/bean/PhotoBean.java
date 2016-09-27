package com.android.lf.lroid.m.bean;

import java.io.Serializable;

public class PhotoBean implements Serializable {

    private int id;
    private String path;
    public PhotoBean(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
