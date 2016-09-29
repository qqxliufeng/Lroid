package com.android.lf.lroid.p;

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

import retrofit2.http.PUT;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/9/22.
 */

public class UserHelperPresenter extends BasePresenter {

    public static final int REQUEST_CODE_LOGIN = 0x0;
    public static final int REQUEST_CODE_REGISTER = 0x1;

    //user select
    public void normalLogin(final String userName, final String userPassword) {
        try {
            if (iPresentListener != null) {
                Observable.just(userName).map(new Func1<String, UserBean>() {
                    @Override
                    public UserBean call(String s) {
                        SystemClock.sleep(3000);
                        Cursor cursor = mContext.getContentResolver().query(DataProvider.USER_URI, UserTable.PROJECTION, UserTable.PHONE + " = ? and " + UserTable.PASSWORD + " = ? ", new String[]{userName, userPassword}, null);
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
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                if (checkNullPresent()) {
                                    iPresentListener.onRequestStart(REQUEST_CODE_LOGIN);
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UserBean>() {
                            @Override
                            public void onCompleted() {
                                if (checkNullPresent()) {
                                    iPresentListener.onRequestEnd(REQUEST_CODE_LOGIN);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (checkNullPresent()) {
                                    iPresentListener.onRequestEnd(REQUEST_CODE_LOGIN);
                                    iPresentListener.onRequestFail(REQUEST_CODE_LOGIN, e);
                                }
                            }

                            @Override
                            public void onNext(UserBean o) {
                                if (checkNullPresent()) {
                                    iPresentListener.onRequestSuccess(REQUEST_CODE_LOGIN, o);
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fastLogin(final String phone) {
        Observable.just(phone).map(new Func1<String, UserBean>() {
            @Override
            public UserBean call(String s) {
                Cursor cursor = mContext.getContentResolver().query(DataProvider.USER_URI, UserTable.PROJECTION, UserTable.PHONE + " = ? ", new String[]{s}, null);
                if (cursor != null) {
                    SystemClock.sleep(2000);
                    int result = cursor.getCount();
                    if (result == 0) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(UserTable.NAME, phone);
                        contentValues.put(UserTable.FACE, "");
                        contentValues.put(UserTable.PASSWORD, "lroid");
                        contentValues.put(UserTable.NICK_NAME, "lroid");
                        contentValues.put(UserTable.PHONE, phone);
                        contentValues.put(UserTable.PERSONALIZED_SIGNATURE, "");
                        contentValues.put(UserTable.SEX, 0);
                        if (mContext.getContentResolver().insert(DataProvider.USER_URI, contentValues) != null) {
                            UserBean.getInstance().setNickName("lroid");
                            UserBean.getInstance().setPersonalizedSignature("");
                            UserBean.getInstance().setFace("");
                            UserBean.getInstance().setName(phone);
                            UserBean.getInstance().setPhone(phone);
                            UserBean.getInstance().setSex(0);
                            UserBean.getInstance().setPassword("lroid");
                        } else {
                            cursor.close();
                            return null;
                        }
                    } else {
                        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                            UserBean.getInstance().setFace(cursor.getString(cursor.getColumnIndex(UserTable.FACE)));
                            UserBean.getInstance().setName(cursor.getString(cursor.getColumnIndex(UserTable.NAME)));
                            UserBean.getInstance().setNickName(cursor.getString(cursor.getColumnIndex(UserTable.NICK_NAME)));
                            UserBean.getInstance().setPassword(cursor.getString(cursor.getColumnIndex(UserTable.PASSWORD)));
                            UserBean.getInstance().setPersonalizedSignature(cursor.getString(cursor.getColumnIndex(UserTable.PERSONALIZED_SIGNATURE)));
                            UserBean.getInstance().setPhone(cursor.getString(cursor.getColumnIndex(UserTable.PHONE)));
                            UserBean.getInstance().setSex(cursor.getInt(cursor.getColumnIndex(UserTable.SEX)));
                        }
                    }
                    cursor.close();
                }
                return UserBean.getInstance();
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestStart(REQUEST_CODE_LOGIN);
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestEnd(REQUEST_CODE_LOGIN);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestFail(REQUEST_CODE_LOGIN, e);
                            iPresentListener.onRequestEnd(REQUEST_CODE_LOGIN);
                        }
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestSuccess(REQUEST_CODE_LOGIN, userBean);
                        }
                    }
                });
    }

    //user delete
    public void logout(final String where, final String... args) {
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
    public void modifyUserInfo(final ContentValues contentValues, final String where, final String... args) {
        if (contentValues == null)
            throw new IllegalArgumentException("contentValues must be not null");
        if (TextUtils.isEmpty(where))
            throw new IllegalArgumentException("where must be not null or \"\" ");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                SystemClock.sleep(1000);
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
                        Logger.e(e.getMessage(),"modify user information error ");

                    }
                    @Override
                    public void onNext(Integer integer) {
                        if (checkNullPresent()){
                            iPresentListener.onRequestSuccess(0x0,integer);
                        }
                    }
                });
    }

    //user insert
    public void register(final UserBean userBean) {
        Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
                SystemClock.sleep(3000);
                ContentValues contentValues = new ContentValues();
                contentValues.put(UserTable.NAME, userBean.getName());
                contentValues.put(UserTable.FACE, userBean.getFace());
                contentValues.put(UserTable.PASSWORD, userBean.getPassword());
                contentValues.put(UserTable.NICK_NAME, userBean.getNickName());
                contentValues.put(UserTable.PHONE, userBean.getPhone());
                contentValues.put(UserTable.PERSONALIZED_SIGNATURE, userBean.getPersonalizedSignature());
                contentValues.put(UserTable.SEX, userBean.getSex());
                Uri result = mContext.getContentResolver().insert(DataProvider.USER_URI, contentValues);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestStart(REQUEST_CODE_REGISTER);
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Uri>() {
                    @Override
                    public void onCompleted() {
                        if (checkNullPresent()) {
                            iPresentListener.onRequestEnd(REQUEST_CODE_REGISTER);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "error");
                        if (checkNullPresent()) {
                            iPresentListener.onRequestFail(REQUEST_CODE_REGISTER, e);
                            iPresentListener.onRequestEnd(REQUEST_CODE_REGISTER);
                        }
                    }

                    @Override
                    public void onNext(Uri uri) {
                        Logger.e(uri.getPath());
                        if (checkNullPresent()) {
                            iPresentListener.onRequestSuccess(REQUEST_CODE_REGISTER, uri);
                        }
                    }
                });
    }

}
