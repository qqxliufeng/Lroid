package com.android.lf.lroid.m.interfaces;

/**
 * Created by feng on 2016/8/2.
 */

public interface IBaseListModelInterface extends IBaseModelInterface{

    public void onLoadMore(String url, OnModelToPresenterListener onModelToPresenterListener);

    public void onRefresh(String url, OnModelToPresenterListener onModelToPresenterListener);


}
