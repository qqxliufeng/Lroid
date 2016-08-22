package com.android.lf.lroid.interfaces;

/**
 * Created by feng on 2016/8/22.
 */

public interface IPresentListener {

    public void onRequestStart();

    public void onRequestFail(Throwable e);

    public void onRequestSuccess(String result);

    public void onRequestEnd();

}
