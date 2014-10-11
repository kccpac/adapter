package com.adapter;

public enum requestCode {
	INTENT_TTS_DATA_CHECK(1),
	INTENT_ACTIVITY_ONE(2),
	INTENT_ACTIVITY_TWO(3),
	INTENT_ACTIVITY_THREE(4);
	
	private int val;
	
	requestCode(int val)
	{
		this.val = val;
	}
	
	public int getVal()
	{
		return this.val;
	}
	
}
