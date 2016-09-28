package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lf.lroid.interfaces.IPresentListener;
import com.android.lf.lroid.interfaces.OnUserLoginSuccessListener;
import com.android.lf.lroid.v.activity.BaseActivity;

import butterknife.ButterKnife;


/**
 * Created by feng on 2016/8/1.
 */

public abstract class LroidBaseFragment extends Fragment implements View.OnClickListener,IPresentListener,OnUserLoginSuccessListener{

    protected Context mContext;

    protected ProgressDialog mProgressDialog;

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

    protected void  finishActivity(){
        ((BaseActivity)mContext).finish();
    }

    @Override
    public void onUserLoginSuccess() {

    }

    /**
     * 检查权限
     * @param permission 权限名称
     * @return <code>true</code>若是系统版本大于23，并且系统没有授权
     */
    public boolean checkPermission(String permission){
       return Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(mContext,permission) != PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(int code,String... permission){
        requestPermissions(permission,code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            onPermissionSuccess(requestCode);
        }else {
            onPermissionFail(requestCode);
        }
    }

    protected void onPermissionSuccess(int code){
    }

    protected void onPermissionFail(int code){
    }


}
