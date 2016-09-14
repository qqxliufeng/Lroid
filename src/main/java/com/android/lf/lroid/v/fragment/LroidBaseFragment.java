package com.android.lf.lroid.v.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lf.lroid.interfaces.IPresentListener;

import butterknife.ButterKnife;


/**
 * Created by feng on 2016/8/1.
 */

public abstract class LroidBaseFragment extends Fragment implements View.OnClickListener,IPresentListener{

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        setComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0){
            throw new IllegalStateException("请先设置布局文件");
        }
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initView(view);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void setComponent();

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRequestStart(int requestID) {

    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {

    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {

    }

    @Override
    public void onRequestEnd(int requestID) {

    }

}
