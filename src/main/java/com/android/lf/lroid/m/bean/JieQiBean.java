package com.android.lf.lroid.m.bean;

/**
 * Created by feng on 2016/9/12.
 */

public class JieQiBean {
    private String name;
    private String image_url;
    private String content;
    private String now;
    private String time;
    private String lunar;
    private String song;
    private String detail_info_url;
    private int type;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getDetail_info_url() {
        return detail_info_url;
    }

    public void setDetail_info_url(String detail_info_url) {
        this.detail_info_url = detail_info_url;
    }
}
