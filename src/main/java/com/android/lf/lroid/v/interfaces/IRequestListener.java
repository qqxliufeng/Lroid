package com.android.lf.lroid.v.interfaces;

/**
 * Created by feng on 2016/8/1.
 */

public interface IRequestListener<T> {

    public abstract void onRequestStart(int requestId);

    public abstract void onRequestSuccess(int requestId, T result);

    public abstract void onRequestFailed(int requestId, String error);

    public abstract void onRequestEnd(int requestId);


}
