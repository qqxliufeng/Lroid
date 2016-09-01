package com.android.lf.lroid.interfaces;

/**
 * Created by feng on 2016/8/22.
 */

public interface IPresentListener {

    public void onRequestStart(int requestID);

    public void onRequestFail(int requestID,Throwable e);

    public void onRequestSuccess(int requestID,String result);

    public void onRequestEnd(int requestID);

}
