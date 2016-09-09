package com.android.lf.lroid.p.common;



import android.app.Activity;

import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.component.UserManagerService;
import com.android.lf.lroid.interfaces.IPresentListener;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by feng on 2016/8/1.
 */

public class BasePresenter {

    protected IPresentListener iPresentListener;

    @Inject
    UserManagerService userManagerService;

    public BasePresenter(){
        LroidApplication.getInstance().getPresentComponent().inject(this);
    }

    protected boolean checkNullPresent(){
       return iPresentListener != null;
    }

    public void setPresentListener(IPresentListener iPresentListener) {
        this.iPresentListener = iPresentListener;
    }

    //    private Reference<T> mReferences;
//
//    public void onAttachView(T view){
//        mReferences = new WeakReference<T>(view);
//    }
//
//    public boolean isAttachView(){
//        return mReferences !=null && mReferences.get()!=null;
//    }
//
//    public T getView(){
//        return mReferences.get();
//    }
//
//    public void onDetachView(){
//        if (mReferences !=null){
//            mReferences.clear();
//            mReferences = null;
//        }
//    }
//
//    protected abstract IBaseModelInterface createInterface();
}