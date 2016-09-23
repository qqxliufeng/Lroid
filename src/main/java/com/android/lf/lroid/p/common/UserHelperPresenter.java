package com.android.lf.lroid.p.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.m.tables.UserTable;
import com.orhanobut.logger.Logger;

import java.util.concurrent.Future;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/9/22.
 */

public class UserHelperPresenter extends BasePresenter {

    //user select
    public void selectUser(final String userName,final String userPassword, final String... args) {
        try {
            if (iPresentListener != null) {
                iPresentListener.onRequestStart(0x0);
                Observable.just(userName).map(new Func1<String, UserBean>() {
                    @Override
                    public UserBean call(String s) {
                        SystemClock.sleep(3000);
                        Cursor cursor = mContext.getContentResolver().query(DataProvider.USER_URI, UserTable.PROJECTION, s + " = ? and "+ userPassword +" = ? ", args, null);
                        if (cursor != null) {
                            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                                UserBean.getInstance().setFace(cursor.getString(cursor.getColumnIndex(UserTable.FACE)));
                                UserBean.getInstance().setName(cursor.getString(cursor.getColumnIndex(UserTable.NAME)));
                                UserBean.getInstance().setNickName(cursor.getString(cursor.getColumnIndex(UserTable.NICK_NAME)));
                                UserBean.getInstance().setPassword(cursor.getString(cursor.getColumnIndex(UserTable.PASSWORD)));
                                UserBean.getInstance().setPersonalizedSignature(cursor.getString(cursor.getColumnIndex(UserTable.PERSONALIZED_SIGNATURE)));
                                UserBean.getInstance().setPhone(cursor.getString(cursor.getColumnIndex(UserTable.PHONE)));
                                UserBean.getInstance().setSex(cursor.getInt(cursor.getColumnIndex(UserTable.SEX)));
                            }
                            cursor.close();
                        }
                        return UserBean.getInstance();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {
                        iPresentListener.onRequestEnd(0x0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iPresentListener.onRequestFail(0x0, e);
                    }

                    @Override
                    public void onNext(UserBean o) {
                        iPresentListener.onRequestSuccess(0x0, o);
                    }
                });


//                Observable.just(userName).map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        Cursor cursor = mContext.getContentResolver().query(DataProvider.USER_URI, UserTable.PROJECTION, s + " = ? ", args, null);
//                        if (cursor!=null){
//                            int result = cursor.getCount();
//                            cursor.close();
//                            return result;
//                        }
//                        return 0;
//                    }
//                }).flatMap(new Func1<Integer, Observable<UserBean>>() {
//                    @Override
//                    public Observable<UserBean> call(Integer integer) {
//                        if (integer<=0){
//                            return null;
//                        }
//                        return Observable.create(new Observable.OnSubscribe<UserBean>(){
//
//                            @Override
//                            public void call(Subscriber<? super UserBean> subscriber) {
//                                Cursor cursor = mContext.getContentResolver().query(DataProvider.USER_URI, UserTable.PROJECTION, userName + " = ? ", args, null);
//                                if (cursor != null) {
//                                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//                                        UserBean.getInstance().setFace(cursor.getString(cursor.getColumnIndex(UserTable.FACE)));
//                                        UserBean.getInstance().setName(cursor.getString(cursor.getColumnIndex(UserTable.NAME)));
//                                        UserBean.getInstance().setNickName(cursor.getString(cursor.getColumnIndex(UserTable.NICK_NAME)));
//                                        UserBean.getInstance().setPassword(cursor.getString(cursor.getColumnIndex(UserTable.PASSWORD)));
//                                        UserBean.getInstance().setPersonalizedSignature(cursor.getString(cursor.getColumnIndex(UserTable.PERSONALIZED_SIGNATURE)));
//                                        UserBean.getInstance().setPhone(cursor.getString(cursor.getColumnIndex(UserTable.PHONE)));
//                                        UserBean.getInstance().setSex(cursor.getInt(cursor.getColumnIndex(UserTable.SEX)));
//                                    }
//                                    cursor.close();
//                                }
//                            }
//                        });
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UserBean>() {
//                    @Override
//                    public void onCompleted() {
//                        iPresentListener.onRequestEnd(0x0);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(UserBean userBean) {
//                        Logger.e("user name " + userBean.getName());
//                    }
//                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //user delete
    public void deleteUser(final String where, final String... args) {
        if (TextUtils.isEmpty(where))
            throw new IllegalArgumentException("where must be not null or \"\" ");
        Observable.just(where).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return mContext.getContentResolver().delete(DataProvider.USER_URI, where + " = ? ", args);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Logger.e("update result --> " + integer);
            }
        });
    }

    //user update
    public void updateUser(final ContentValues contentValues, final String where, final String... args) {
        if (contentValues == null)
            throw new IllegalArgumentException("contentValues must be not null");
        if (TextUtils.isEmpty(where))
            throw new IllegalArgumentException("where must be not null or \"\" ");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int result = mContext.getContentResolver().update(DataProvider.USER_URI, contentValues, where + " = ? ", args);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onStart() {
                        Logger.e("update result --> " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.e("update result --> " + integer);
                    }
                });
    }

    //user insert
    public void insertUser() {
        Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(UserTable.NAME, UserBean.getInstance().getName());
                contentValues.put(UserTable.FACE, UserBean.getInstance().getFace());
                contentValues.put(UserTable.PASSWORD, UserBean.getInstance().getPassword());
                contentValues.put(UserTable.NICK_NAME, UserBean.getInstance().getNickName());
                contentValues.put(UserTable.PHONE, UserBean.getInstance().getPhone());
                contentValues.put(UserTable.PERSONALIZED_SIGNATURE, UserBean.getInstance().getPersonalizedSignature());
                contentValues.put(UserTable.SEX, UserBean.getInstance().getSex());
                Uri result = mContext.getContentResolver().insert(DataProvider.USER_URI, contentValues);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Uri>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Uri uri) {
                        Logger.e(uri.getPath());
                    }
                });
    }

}
