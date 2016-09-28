package com.android.lf.lroid.p;

import rx.Subscriber;

/**
 * Created by feng on 2016/9/28.
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
    }
}
