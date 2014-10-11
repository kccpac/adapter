package com.test.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class dataStorage extends ContentProvider {

	private static String tag = dataStorage.class.getCanonicalName();
	
	private static SQLiteDatabase db;	

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		Log.i(tag , "insert");
		long row = db.insert(dataContract.TABLE_IMAGEINFO, null, arg1);
		Log.i(tag , "row = " + row);
		return null;

	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Log.i(tag , "onCreate");
		Context context = this.getContext();
		storageDBHelper dbHelper = new storageDBHelper(context);
		db = dbHelper.getWritableDatabase();
		if (db != null)
		{
			return true;
		}
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		Log.i(tag , "query");
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		Log.i(tag , "update");
		return 0;
	}

}
