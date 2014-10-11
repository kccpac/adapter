package com.adapter.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class storageDBHelper extends SQLiteOpenHelper {

	private static final String tag = storageDBHelper.class.getCanonicalName();

	static final String DATABASE_NAME = "test";
	static final int DATABASE_VERSION = 1;
	
	static final String CREATE_TABLE_IMAGEINFO = " CREATE TABLE " + dataContract.TABLE_IMAGEINFO
			   + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
			   + " title TEXT NOT NULL, " 
			   + " description TEXT, "
			   + " url TEXT);";

	public storageDBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public storageDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i(tag  , "onCreate");
		db.execSQL(CREATE_TABLE_IMAGEINFO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
