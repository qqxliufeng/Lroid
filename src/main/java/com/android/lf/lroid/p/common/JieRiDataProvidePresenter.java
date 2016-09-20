package com.android.lf.lroid.p.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.lf.lroid.m.bean.JieQiBean;
import com.android.lf.lroid.m.bean.JieRiBean;
import com.android.lf.lroid.m.data.JieQiData;
import com.android.lf.lroid.m.data.JieRiData;
import com.android.lf.lroid.m.tables.JieQiTable;
import com.android.lf.lroid.m.tables.JieRiTable;

import java.util.ArrayList;

/**
 * Created by feng on 2016/9/20.
 */

public class JieRiDataProvidePresenter extends BasePresenter {

    private Uri mUri;
    private String[] selectArgs;

    private boolean isFirstLoad = true;

    public void getDataFromDB(Uri uri, String... selectArgs) {
        if (uri == null) {
            return;
        }
        mUri = uri;
        this.selectArgs = selectArgs;
        DataLoadAsyncTask asyncTask = new DataLoadAsyncTask();
        asyncTask.execute(uri);
    }

    public void fillDataToDB(Uri uri) {
        DataInitAsyncTask dataInitAsyncTask = new DataInitAsyncTask();
        dataInitAsyncTask.execute(uri);
    }

    class DataLoadAsyncTask extends AsyncTask<Uri, Integer, ArrayList<JieRiBean>> {

        private Cursor cursor;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (iPresentListener != null) {
                iPresentListener.onRequestStart(0x0);
            }
        }

        @Override
        protected ArrayList<JieRiBean> doInBackground(Uri... params) {
            try {
                if (checkNullContext() || checkNullFragment()) {
                    cursor = mContext.getContentResolver().query(params[0], JieRiTable.PROJECTION, JieRiTable.TYPE + " = ? ", selectArgs, null);
                    if (cursor != null) {
                        ArrayList<JieRiBean> results = new ArrayList<>();
                        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                            JieRiBean jieRiBean = new JieRiBean();
                            jieRiBean.setName(cursor.getString(cursor.getColumnIndex(JieRiTable.NAME)));
                            jieRiBean.setTime(cursor.getString(cursor.getColumnIndex(JieRiTable.TIME)));
                            jieRiBean.setType(cursor.getString(cursor.getColumnIndex(JieRiTable.TYPE)));
                            results.add(jieRiBean);
                            Thread.sleep(10);
                        }
                        return results;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TAG", e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<JieRiBean> jieRiBeen) {
            iPresentListener.onRequestEnd(0x0);
            if (jieRiBeen == null && isFirstLoad) {
                DataLoadAsyncTask asyncTask = new DataLoadAsyncTask();
                asyncTask.execute(mUri);
            } else {
                if (iPresentListener != null) {
                    iPresentListener.onRequestSuccess(0x0, jieRiBeen);
                }
            }
        }
    }


    class DataInitAsyncTask extends AsyncTask<Uri, Integer, Integer> {

        private Cursor cursor;
        private int result = -1;

        @Override
        protected void onPreExecute() {
            if (iPresentListener!=null){
                iPresentListener.onRequestStart(0x0);
            }
        }

        @Override
        protected Integer doInBackground(Uri... params) {
            cursor = mContext.getContentResolver().query(params[0], JieRiTable.PROJECTION, null, null, null);
            if (cursor.getCount() == 0) {
                int size = JieRiData.getInstance().getArrayList().size();
                ContentValues[] valuesArrayList = new ContentValues[size];
                for (int i = 0; i < size; i++) {
                    ContentValues contentValues = new ContentValues();
                    JieRiBean jieRiBean = JieRiData.getInstance().getArrayList().get(i);
                    contentValues.put(JieRiTable.NAME, jieRiBean.getName());
                    contentValues.put(JieRiTable.TIME, jieRiBean.getTime());
                    contentValues.put(JieRiTable.TYPE, jieRiBean.getType());
                    valuesArrayList[i] = contentValues;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                result = mContext.getContentResolver().bulkInsert(params[0], valuesArrayList);
            }
            cursor.close();
            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            iPresentListener.onRequestEnd(0x0);
            iPresentListener.onRequestSuccess(0x0,integer);
        }
    }

}
