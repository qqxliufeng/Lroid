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

public class DataProvidePresenter extends BasePresenter {

    ArrayList<JieQiBean> jieQiBeanArrayList = new ArrayList<JieQiBean>() {
        {
            for (int i = 0; i < JieQiData.getInstance().getJieQiNames().length; i++) {
                JieQiBean jieQiBean = new JieQiBean();
                jieQiBean.setContent(JieQiData.getInstance().getJieQiContents()[i]);
                jieQiBean.setDetail_info_url(JieQiData.getInstance().getJieQiDetailInfoUrl()[i]);
                jieQiBean.setImage_url(JieQiData.getInstance().getJieQiPics()[i]);
                jieQiBean.setTime(JieQiData.getInstance().getJieQiTime()[i]);
                jieQiBean.setName(JieQiData.getInstance().getJieQiNames()[i]);
                if (i > 6) {
                    jieQiBean.setType(JieQiData.getInstance().getTypes()[0]);
                } else if (i >= 6 && i <= 11) {
                    jieQiBean.setType(JieQiData.getInstance().getTypes()[1]);
                } else if (i >= 12 && i <= 17) {
                    jieQiBean.setType(JieQiData.getInstance().getTypes()[2]);
                } else {
                    jieQiBean.setType(JieQiData.getInstance().getTypes()[3]);
                }
                add(jieQiBean);
            }
        }
    };


    public void getDataFromDB() {
        DataProvideLoadAsyncTask dataProvideLoadAsyncTask = new DataProvideLoadAsyncTask();
        dataProvideLoadAsyncTask.execute(DataProvider.JIE_QI_URI);
    }

    class DataProvideLoadAsyncTask extends AsyncTask<Uri, Integer, ArrayList<JieQiBean>> {

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
                dataProvideLoadAsyncTask.execute(DataProvider.JIE_QI_URI);
            } else {
                //更新完成
                iPresentListener.onRequestSuccess(0x0, strings);
            }
        }
    }

    private void fillDataToDB(Uri uri) {
        ContentValues[] valuesArrayList = new ContentValues[jieQiBeanArrayList.size()];
        for (int i = 0; i < jieQiBeanArrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            JieQiBean jieQiBean = jieQiBeanArrayList.get(i);
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

}
