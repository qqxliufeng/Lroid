package com.android.lf.lroid.m.tables;

import android.provider.BaseColumns;

import com.android.lf.lroid.m.database.Column;
import com.android.lf.lroid.m.database.SQLiteTable;

/**
 * Created by feng on 2016/9/22.
 */

public class UserTable implements BaseColumns{

    public static final String TABLE_NAME = "user_table";

    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String NICK_NAME = "nick_name";
    public static final String FACE = "face";
    public static final String PASSWORD = "password";
    public static final String PERSONALIZED_SIGNATURE = "personalized_signature";
    public static final String SEX = "sex";

    public static final String[] PROJECTION = {NAME,
            PASSWORD,
            PHONE,
            NICK_NAME,
            FACE,
            SEX,
            PERSONALIZED_SIGNATURE
    };

    public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
            .addColumn(NAME, Column.DataType.TEXT)
            .addColumn(PASSWORD, Column.DataType.TEXT)
            .addColumn(FACE, Column.DataType.TEXT)
            .addColumn(PERSONALIZED_SIGNATURE, Column.DataType.TEXT)
            .addColumn(PHONE, Column.DataType.TEXT)
            .addColumn(NICK_NAME, Column.DataType.TEXT)
            .addColumn(SEX, Column.DataType.INTEGER);
}
