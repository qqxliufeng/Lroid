package com.android.lf.lroid.m.tables;

import android.provider.BaseColumns;

import com.android.lf.lroid.m.database.Column;
import com.android.lf.lroid.m.database.SQLiteTable;

/**
 * Created by feng on 2016/9/9.
 */

public class JieQiTable implements BaseColumns{
    public static final String TABLE_NAME = "jie_qi_table";//1

    public static final String NAME = "name";//2
    public static final String IMAGE_URL = "image_url";//3
    public static final String CONTENT = "content";//4
    public static final String NOW = "now";
    public static final String TIME = "time";
    public static final String LUNAR = "lunar";
    public static final String SONG = "song";
    public static final String DETAIL_INFO_URL = "detail_info_url";
    public static final String TYPE = "type";
    public static final String TYPE_NAME = "type_name";

    public static final String[] PROJECTION = {NAME,
            IMAGE_URL,
            CONTENT,
            NOW,
            TIME,
            LUNAR,
            SONG,
            DETAIL_INFO_URL,
            TYPE,
            TYPE_NAME
    };

    public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
            .addColumn(NAME, Column.DataType.TEXT)
            .addColumn(IMAGE_URL, Column.DataType.TEXT)
            .addColumn(CONTENT, Column.DataType.TEXT)
            .addColumn(NOW,Column.DataType.TEXT)
            .addColumn(TIME, Column.DataType.TEXT)
            .addColumn(LUNAR, Column.DataType.TEXT)
            .addColumn(SONG, Column.DataType.TEXT)
            .addColumn(DETAIL_INFO_URL, Column.DataType.TEXT)
            .addColumn(TYPE, Column.DataType.INTEGER)
            .addColumn(TYPE_NAME, Column.DataType.TEXT);
}
