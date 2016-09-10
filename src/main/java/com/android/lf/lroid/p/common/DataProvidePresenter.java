package com.android.lf.lroid.p.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.m.tables.JieQiTable;

import java.util.ArrayList;

/**
 * Created by feng on 2016/9/10.
 */

public class DataProvidePresenter extends BasePresenter {

    public void getDataFromDB() {
        DataProvideLoadAsyncTask dataProvideLoadAsyncTask = new DataProvideLoadAsyncTask();
        dataProvideLoadAsyncTask.execute(DataProvider.JIE_QI_URI);
    }

    class DataProvideLoadAsyncTask extends AsyncTask<Uri, Integer, ArrayList<String>> {

        private Cursor cursor;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(Uri... params) {
            try {
                if (checkNullContext() || checkNullFragment()) {
                    cursor = mContext.getContentResolver().query(params[0], JieQiTable.PROJECTION, null, null, null);
                    Log.e("TAG","count is ==========>    "+cursor.getCount());
                    if (cursor!=null) {
                        //如果查询的结果集是空的，那么就去网络请求数据
                        if (cursor.getCount() == 0){
                            fillDataToDB(params[0]);
                        }else {
                            ArrayList<String> results = new ArrayList<>();
                            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
                                results.add(cursor.getString(cursor.getColumnIndex(JieQiTable.NAME)));
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
        protected void onPostExecute(ArrayList<String> strings) {
            if (strings == null){
                DataProvideLoadAsyncTask dataProvideLoadAsyncTask = new DataProvideLoadAsyncTask();
                dataProvideLoadAsyncTask.execute(DataProvider.JIE_QI_URI);
            }else {
                //更新完成
                iPresentListener.onRequestSuccess(0x0,strings);
            }
        }
    }

    private void fillDataToDB(Uri uri) {
        ContentValues[] valuesArrayList = new ContentValues[24];
        for (int i = 0; i < 24; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(JieQiTable.NAME,i+"");
            valuesArrayList[i] = contentValues;
        }
        mContext.getContentResolver().bulkInsert(uri, valuesArrayList);
    }
}
