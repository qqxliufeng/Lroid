package com.android.lf.lroid.p.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.lf.lroid.m.bean.JieQiBean;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.m.tables.JieQiTable;

import java.util.ArrayList;

/**
 * Created by feng on 2016/9/10.
 */

public class DataProvidePresenter extends BasePresenter {

    private String[] jieQiNames = new String[]{
            "立春","雨水","惊蛰","春分","清明","谷雨",
            "立夏","小满","芒种","夏至","小暑","大暑",
            "立秋","处暑","白露","秋分","寒露","霜降",
            "立冬","小雪","大雪","冬至","小寒","大寒"
    };

    private String[] jieQiTime = new String[]{
            "2月3-5日","2月18-20日","3月5-7日","3月20-22日","4月4-6日","4月19-21日",
            "5月5-7日","5月20-22日","6月5-7日","6月21-22日","7月6-8日","7月22日-24日",
            "8月7-9日","8月22-24日","9月7-9日","9月22-24日","10月8-9日","10月23-24日",
            "11月7-8日","11月22-23日","12月6-8日","12月21-23日","1月5-7日","1月20-21日"
    };

    private String[] jieQiPics = new String[]{
            "http://hiphotos.baidu.com/zhixin/abpic/item/aa251d4f78f0f736cb3d0a480855b319eac4137c.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/310f3b1f95cad1c8104c2b527d3e6709c83d5195.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/dc15484e9258d1090b3b51c4d358ccbf6d814da9.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/0d729944ebf81a4c6cab36bad52a6059242da6ed.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/425773224f4a20a4b3212cb492529822720ed043.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b87985504fc2d562a048a495e51190ef77c66cc2.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/3853ad1bb051f819cb6ba801d8b44aed2f73e7f4.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/eab9044c510fd9f97ecee51d272dd42a2934a4ae.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/27d647ee3d6d55fb0a991e556f224f4a20a4dd23.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/d8b8c92a6059252dd51f18b8369b033b5ab5b9c6.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/8cf0d51349540923e89b1a0b9058d109b2de4991.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/72ccb7773912b31bacb2356f8418367adbb4e15f.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/7e7f7909c93d70cf3fda047bfadcd100baa12b60.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/4075890a304e251f9149428ea586c9177e3e53ce.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/86d5bac27d1ed21b2ed688aeaf6eddc451da3f1a.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/90cebeec08fa513dbc5a2e923f6d55fbb3fbd94f.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/cbc17b380cd79123dd8ea102af345982b2b7801e.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/7787b9efce1b9d1659996516f1deb48f8c546428.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b110e6198618367a083251aa2c738bd4b21ce5cb.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/4a77b2af2edda3ccc294175403e93901213f9217.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b74124f33a87e95051101cd412385343faf2b4c0.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b6045da98226cffca876a00ebb014a90f703eaeb.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/d048adde9c82d158b2a866cb820a19d8bc3e4266.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/7787b9efce1b9d1617412b12f1deb48f8d546484.jpg",
    };


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

    private void initData() {

    }
}
