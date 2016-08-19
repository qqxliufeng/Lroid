package com.android.lf.lroid.v.interfaces;

/**
 * Created by feng on 2016/8/1.
 */

public interface IBaseViewInterface<T> extends IRequestListener<T>{
    public abstract Class<T> getType();
}
