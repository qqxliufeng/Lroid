package com.android.lf.lroid.m.common;


import com.android.lf.lroid.m.interfaces.IBaseListModelInterface;
import com.android.lf.lroid.m.interfaces.OnModelToPresenterListener;

import java.util.ArrayList;

/**
 * Created by feng on 2016/8/2.
 */

public class BaseListModelImpl implements IBaseListModelInterface {

    @Override
    public void onLoadMore(String url, final OnModelToPresenterListener onModelToPresenterListener) {
        new Thread(){
            @Override
            public void run() {
                ArrayList<String> arrayList = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    arrayList.add("item is --> " + i);
                }
                onModelToPresenterListener.onModelToPresenter(arrayList);
            }
        }.start();
    }

    @Override
    public void onRefresh(String url, OnModelToPresenterListener onModelToPresenterListener) {

    }

    @Override
    public void onLoadingData(String url, OnModelToPresenterListener onModelToPresenterListener) {

    }
}
