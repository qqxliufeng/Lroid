package com.android.lf.lroid.p;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;

import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.m.tables.UserTable;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/9/22.
 */

public class UserHelperPresenter extends BasePresenter {

    public static final int REQUEST_CODE_NORMAL_LOGIN = 0x0;
    public static final int REQUEST_CODE_REGISTER = 0x1;
    public static final int REQUEST_CODE_MODIFY = 0x2;
    public static final int REQUEST_CODE_LOGOUT = 0x3;
    public static final int REQUEST_CODE_FAST_LOGIN = 0x4;

    @Override
    protected <T, R> R doSomething(int requestID, T[] ts) {
        switch (requestID) {
            case REQUEST_CODE_NORMAL_LOGIN:
                return normalLogin((String) ts[0], (String) ts[1]);
            case REQUEST_CODE_REGISTER:
                break;
            case REQUEST_CODE_MODIFY:
                return modifyUserInfo((ContentValues) ts[0], (String) ts[1], (String) ts[2]);
            case REQUEST_CODE_LOGOUT:
                break;
            case REQUEST_CODE_FAST_LOGIN:
                return fastLogin((String) ts[0]);
        }
        return null;
    }

    //user select
    private  <R> R normalLogin(final String userName, final String userPassword) {
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
        return (R) UserBean.getInstance();
    }

    private <R> R fastLogin(final String phone) {
        Cursor cursor = mContext.getContentResolver().query(DataProvider.USER_URI, UserTable.PROJECTION, UserTable.PHONE + " = ? ", new String[]{phone}, null);
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
        return (R) UserBean.getInstance();
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
    private <R> R modifyUserInfo(final ContentValues contentValues, final String where, final String args) {
        SystemClock.sleep(1000);
        return (R) (Integer) mContext.getContentResolver().update(DataProvider.USER_URI, contentValues, where + " = ? ", new String[]{args});
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
