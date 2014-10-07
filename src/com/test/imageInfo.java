package com.test;

import java.net.URL;

public class imageInfo {
	
	private String title;
	private String description;
	private URL url;
	
	public imageInfo(String title, String description, URL url)
	{
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public URL getSourceURL()
	{
		return url;
	}
}

