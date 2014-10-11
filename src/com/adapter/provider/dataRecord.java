package com.adapter.provider;

import com.adapter.imageInfo;

public class dataRecord {

	private static int id = 0;
	// Unique ID
	private final int _id;

	private imageInfo image_info;
	
	public dataRecord(imageInfo info)
	{
		this.image_info = new imageInfo(info.getTitle(), info.getDescription(), info.getSourceURL());
		_id = id++;
	}
	
	public imageInfo getImageInfo()
	{
		return image_info;
	}
	
	public int getId()
	{
		return _id;
	}
	
	
}
