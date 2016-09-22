package com.android.lf.lroid.m.bean;

/**
 * Created by feng on 2016/9/22.
 */

public class UserBean {

    private static UserBean instance = null;

    private UserBean() {
    }

    public static UserBean getInstance() {
        if (instance == null) {
            synchronized (UserBean.class) {
                if (instance == null) {
                    instance = new UserBean();
                }
            }
        }
        return instance;
    }

    private String name;
    private String phone;
    private String nickName;
    private String face;
    private String password;
    private String personalizedSignature;
    private int sex;

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
