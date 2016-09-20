package com.android.lf.lroid.p.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.lf.lroid.m.bean.JieQiBean;
import com.android.lf.lroid.m.data.JieQiData;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.m.tables.JieQiTable;

import java.util.ArrayList;

/**
 * Created by feng on 2016/9/10.
 */

public class JieQiDataProvidePresenter extends BasePresenter {

    private Uri mUri;

    public void getDataFromDB(Uri uri) {
        if (uri == null){
            return;
        }
        mUri = uri;
        DataProvideLoadAsyncTask dataProvideLoadAsyncTask = new DataProvideLoadAsyncTask();
        dataProvideLoadAsyncTask.execute(mUri);
    }

    private void fillDataToDB(Uri uri) {
        ContentValues[] valuesArrayList = new ContentValues[JieQiData.getInstance().getJieQiBeanArrayList().size()];
        for (int i = 0; i < JieQiData.getInstance().getJieQiBeanArrayList().size(); i++) {
            ContentValues contentValues = new ContentValues();
            JieQiBean jieQiBean = JieQiData.getInstance().getJieQiBeanArrayList().get(i);
            contentValues.put(JieQiTable.NAME, jieQiBean.getName());
            contentValues.put(JieQiTable.CONTENT, jieQiBean.getContent());
            contentValues.put(JieQiTable.DETAIL_INFO_URL, jieQiBean.getDetail_info_url());
            contentValues.put(JieQiTable.IMAGE_URL, jieQiBean.getImage_url());
            contentValues.put(JieQiTable.TIME, jieQiBean.getTime());
            contentValues.put(JieQiTable.TYPE, jieQiBean.getType());
            contentValues.put(JieQiTable.TYPE_NAME, jieQiBean.getTypeName());
            valuesArrayList[i] = contentValues;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mContext.getContentResolver().bulkInsert(uri, valuesArrayList);
    }

    private void initData() {
    }

    private class DataProvideLoadAsyncTask extends AsyncTask<Uri, Integer, ArrayList<JieQiBean>> {

        private Cursor cursor;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iPresentListener.onRequestStart(0x0);
        }

        @Override
        protected ArrayList<JieQiBean> doInBackground(Uri... params) {
            try {
                if (checkNullContext() || checkNullFragment()) {
                    cursor = mContext.getContentResolver().query(params[0], JieQiTable.PROJECTION, null, null, null);
                    if (cursor != null) {
                        //如果查询的结果集是空的，那么就去网络请求数据
                        if (cursor.getCount() == 0) {
                            fillDataToDB(params[0]);
                        } else {
                            ArrayList<JieQiBean> results = new ArrayList<>();
                            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                                JieQiBean jieQiBean = new JieQiBean();
                                jieQiBean.setImage_url(cursor.getString(cursor.getColumnIndex(JieQiTable.IMAGE_URL)));
                                jieQiBean.setName(cursor.getString(cursor.getColumnIndex(JieQiTable.NAME)));
                                jieQiBean.setContent(cursor.getString(cursor.getColumnIndex(JieQiTable.CONTENT)));
                                jieQiBean.setDetail_info_url(cursor.getString(cursor.getColumnIndex(JieQiTable.DETAIL_INFO_URL)));
                                jieQiBean.setTime(cursor.getString(cursor.getColumnIndex(JieQiTable.TIME)));
                                jieQiBean.setType(cursor.getInt(cursor.getColumnIndex(JieQiTable.TYPE)));
                                results.add(jieQiBean);
                                Thread.sleep(50);
                            }
                            return results;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<JieQiBean> strings) {
            iPresentListener.onRequestEnd(0x0);
            if (strings == null) {
                DataProvideLoadAsyncTask dataProvideLoadAsyncTask = new DataProvideLoadAsyncTask();
                dataProvideLoadAsyncTask.execute(mUri);
            } else {
                //更新完成
                iPresentListener.onRequestSuccess(0x0, strings);
            }
        }
    }

}
