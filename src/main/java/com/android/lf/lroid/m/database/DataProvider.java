package com.android.lf.lroid.m.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.lf.lroid.m.tables.JieQiTable;

public class DataProvider extends ContentProvider {

	private Context mContext;
	private static final String HOST_NAME = "content://";
	private static final String AUTHORITY = "com.android.jn.car.msxc";
	private static UriMatcher matcher;

	public static final Uri JIE_QI_URI = Uri.parse(HOST_NAME  + AUTHORITY + "/jieqi");
	public static final Uri FEAST_URI = Uri.parse(HOST_NAME  + AUTHORITY + "/feast");

	private static DatabaseHelper helper;

	public static final Object DBLock = new Object();

	private static final int JIE_QI_URI_CODE = 1;
	private static final int FEAST_URI_CODE = 2;

	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY,"jieqi",JIE_QI_URI_CODE);
        matcher.addURI(AUTHORITY,"feast",FEAST_URI_CODE);
	}

	private String matchTable(Uri uri) {
		switch (matcher.match(uri)){
            case JIE_QI_URI_CODE:
                return JieQiTable.TABLE_NAME;
            case FEAST_URI_CODE:

                break;
		}
		return "";
	}

	@Override
	public boolean onCreate() {
		mContext = getContext();
		helper = DatabaseHelper.getDatabaseHelper(mContext);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String tableName = matchTable(uri);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = database.query(tableName, projection, selection, selectionArgs, null , null, sortOrder);
		return cursor;
	}


	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		synchronized (DBLock) {
			String tableName = matchTable(uri);
			SQLiteDatabase database = helper.getWritableDatabase();
			database.beginTransaction();
			long rowId = 0;
			try {
				rowId = database.insert(tableName, BaseColumns._ID, values);
				database.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				database.endTransaction();
			}
			if (rowId > 0) {
				Uri newUri = ContentUris.withAppendedId(uri, rowId);
				mContext.getContentResolver().notifyChange(uri, null);
				return newUri;
			}
			throw new SQLException("Failed to insert row into " + uri);
		}
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		synchronized (DBLock) {
			String tableName = matchTable(uri);
			SQLiteDatabase database = helper.getWritableDatabase();
			database.beginTransaction();
			try {
				for (ContentValues contentValues : values) {
					database.insertWithOnConflict(tableName, BaseColumns._ID,
							contentValues, SQLiteDatabase.CONFLICT_IGNORE);
				}
				database.setTransactionSuccessful();
				mContext.getContentResolver().notifyChange(uri, null);
				return values.length;
			} catch (Exception e) {
				Log.e(DataProvider.class.getSimpleName(), e.getMessage());
			} finally {
				database.endTransaction();
			}
			throw new SQLException("Failed to insert row into " + uri);
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		synchronized (DBLock) {
			SQLiteDatabase db = helper.getWritableDatabase();
			int count = 0;
			String table = matchTable(uri);
			db.beginTransaction();
			try {
				count = db.delete(table, selection, selectionArgs);
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
			getContext().getContentResolver().notifyChange(uri, null);
			return count;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		synchronized (DBLock) {
			SQLiteDatabase db = DatabaseHelper.getDatabaseHelper(mContext)
					.getWritableDatabase();
			int count;
			String table = matchTable(uri);
			db.beginTransaction();
			try {
				count = db.update(table, values, selection, selectionArgs);
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
			getContext().getContentResolver().notifyChange(uri, null);
			return count;
		}
	}

	@Override
	public Bundle call(String method, String arg, Bundle extras) {
		return super.call(method, arg, extras);
	}

}
