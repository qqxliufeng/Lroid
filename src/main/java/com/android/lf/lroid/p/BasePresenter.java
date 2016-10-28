package com.android.lf.lroid.p;


import android.content.Context;

import com.android.lf.lroid.application.LroidApplication;
import com.android.lf.lroid.component.ApiService;
import com.android.lf.lroid.interfaces.IPresentListener;
import com.android.lf.lroid.v.fragment.LroidBaseFragment;

import java.lang.ref.WeakReference;

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

    protected WeakReference<IPresentListener> iPresentListener;
    protected WeakReference<Context> mContext;
    protected WeakReference<LroidBaseFragment> baseFragment;

    @Inject
    ApiService userManagerService;

    public BasePresenter() {
        LroidApplication.getInstance().getPresentComponent().inject(this);
    }

    protected boolean checkNullPresent() {
        return iPresentListener != null && iPresentListener.get() != null;
    }

    protected boolean checkNullContext() {
        return mContext != null && mContext.get() != null;
    }

    protected boolean checkNullFragment() {
        return baseFragment != null && baseFragment.get() != null;
    }

    public void setPresentListener(IPresentListener iPresentListener) {
        this.iPresentListener = new WeakReference<IPresentListener>(iPresentListener);
    }

    public void setContext(Context mContext) {
        this.mContext = new WeakReference<Context>(mContext);
    }

    public void setFragment(LroidBaseFragment baseFragment) {
        this.baseFragment = new WeakReference<LroidBaseFragment>(baseFragment);
        iPresentListener = new WeakReference<IPresentListener>(baseFragment);
        mContext = new WeakReference<Context>(baseFragment.getContext());
    }

    public Context getContext() {
        return mContext != null ? mContext.get() : null;
    }

    public LroidBaseFragment getBaseFragment() {
        return baseFragment != null ? baseFragment.get() : null;
    }

    protected IPresentListener getPresentListener() {
        return iPresentListener.get();
    }

    @SafeVarargs
    public final <T, R> void doSomethingWithRxJavaMap(final int requestId, T... t) {
        Observable.just(t).map(new Func1<T[], R>() {
            @Override
            public R call(T[] ts) {
                return doSomething(requestId, ts);
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (checkNullPresent()) {
                            getPresentListener().onRequestStart(requestId);
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<R>() {
                    @Override
                    public void onCompleted() {
                        if (checkNullPresent()) {
                            iPresentListener.get().onRequestEnd(requestId);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (checkNullPresent()) {
                            iPresentListener.get().onRequestEnd(requestId);
                            iPresentListener.get().onRequestFail(requestId, e);
                        }
                    }

                    @Override
                    public void onNext(R r) {
                        if (checkNullPresent()) {
                            getPresentListener().onRequestSuccess(requestId, r);
                        }
                    }
                });
    }


    protected <T, R> R doSomething(int requestID, T[] ts) {
        return null;
    }

    public void detach() {
        if (iPresentListener != null) {
            iPresentListener.clear();
            iPresentListener = null;
        }
        if (baseFragment != null) {
            baseFragment.clear();
            baseFragment = null;
        }
        if (mContext != null) {
            mContext.clear();
            mContext = null;
        }
    }
}
