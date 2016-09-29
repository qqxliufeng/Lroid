package com.android.lf.lroid.p;


import android.content.Context;

import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.component.ApiService;
import com.android.lf.lroid.interfaces.IPresentListener;
import com.android.lf.lroid.v.fragment.LroidBaseFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/8/1.
 */

public class BasePresenter {

    protected IPresentListener iPresentListener;
    protected Context mContext;
    protected LroidBaseFragment baseFragment;

    @Inject
    ApiService userManagerService;

    public BasePresenter() {
        LroidApplication.getInstance().getPresentComponent().inject(this);
    }

    protected boolean checkNullPresent() {
        return iPresentListener != null;
    }

    protected boolean checkNullContext() {
        return mContext != null;
    }

    protected boolean checkNullFragment() {
        return baseFragment != null;
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

    @SafeVarargs
    public final <T, R> void doSomethingWithRxJavaMap(final int requestId, T... t) {
        Observable.just(t).map(new Func1<T[], R>() {
            @Override
            public R call(T[] ts) {
                return doSomething(requestId,ts);
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestStart(requestId);
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<R>() {
                    @Override
                    public void onCompleted() {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestEnd(requestId);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestEnd(requestId);
                            iPresentListener.onRequestFail(requestId, e);
                        }
                    }

                    @Override
                    public void onNext(R r) {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestSuccess(requestId, r);
                        }
                    }
                });
    }


    protected <T,R> R doSomething(int requestID,T[] ts){
        return null;
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
