package com.android.lf.lroid.m.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseArray;

import com.android.lf.lroid.m.tables.JieQiTable;


/**
 * Date: 13-8-5
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static SparseArray<DatabaseHelper> sHelpers = new SparseArray<DatabaseHelper>();
    private DatabaseHelper(Context context) {
        super(context, "lroid.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        JieQiTable.TABLE.create(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }


    /**
     * @param context
     * @return
     */
    public static DatabaseHelper getDatabaseHelper(Context context){
        if (context==null){
            throw new NullPointerException("Context is null");
        }
        DatabaseHelper databaseHelper=sHelpers.get(0);
        if (databaseHelper==null){
            databaseHelper=new DatabaseHelper(context);
            sHelpers.put(0,databaseHelper);
        }
        return databaseHelper;
    }
}
