package com.android.lf.lroid.p;



import android.content.Context;

import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.component.UserManagerService;
import com.android.lf.lroid.interfaces.IPresentListener;
import com.android.lf.lroid.v.fragment.LroidBaseFragment;

import javax.inject.Inject;

/**
 * Created by feng on 2016/8/1.
 */

public class BasePresenter {

    protected IPresentListener iPresentListener;
    protected Context mContext;
    protected LroidBaseFragment baseFragment;

    @Inject
    UserManagerService userManagerService;

    public BasePresenter(){
        LroidApplication.getInstance().getPresentComponent().inject(this);
    }

    protected boolean checkNullPresent(){
       return iPresentListener != null;
    }

    protected boolean checkNullContext(){
        return mContext!=null;
    }

    protected boolean checkNullFragment(){
        return baseFragment!=null;
    }

    public void setPresentListener(IPresentListener iPresentListener) {
        this.iPresentListener = iPresentListener;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setFragment(LroidBaseFragment baseFragment) {
        this.baseFragment = baseFragment;
        iPresentListener = baseFragment;
        mContext = baseFragment.getContext();
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