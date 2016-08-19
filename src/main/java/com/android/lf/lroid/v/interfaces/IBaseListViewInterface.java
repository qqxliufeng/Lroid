package com.android.lf.lroid.v.interfaces;

/**
 * Created by feng on 2016/8/2.
 */

public interface IBaseListViewInterface<T> extends IBaseViewInterface<T> {

    public void onLoadMoreComplete(T t);

    public void onRefreshComplete();


}
