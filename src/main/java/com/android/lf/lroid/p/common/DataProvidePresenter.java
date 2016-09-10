package com.android.lf.lroid.p.common;

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
                    if (cursor!=null) {
                        Log.e("TAG","count is ----->  " + cursor.getCount());
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

        }

    }


}
