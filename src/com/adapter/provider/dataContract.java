package com.adapter.provider;

import android.content.ContentResolver;
import android.net.Uri;

public class dataContract {

	static final String TABLE_IMAGEINFO = "imageinfo";
	
	private static final Uri BASE_URI = Uri
			.parse("content://com.adapter.provider.dataStorage/");

	// The URI for this table.
	public static final Uri IMAGEINFO_URI = Uri.withAppendedPath(BASE_URI, TABLE_IMAGEINFO);

	// Mime type for a directory of data items
	public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/dataStorage.data.text";

	// Mime type for a single data item
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/dataStorage.data.text";

	// All columns of this table
//	public static final String[] ALL_COLUMNS = { _ID, DATA };
	 	

}
