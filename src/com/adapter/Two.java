package com.adapter;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class Two extends Activity {

	public final String tag = Two.class.getName();
	
	private SensorManager sm;
	private SensorEventListener orientlistener = null;

	private View view;
	private Paint mPaint;
	private Paint mPenPaint;
	private float mHeadAngle;
	private double PI = Math.PI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(tag, "onCreate");
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_two);
		
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);		
		
		mPaint = new Paint();
		mPenPaint = new Paint();
		mHeadAngle = 0.0F;
		init();
		view = new View(this) {

			public void onDraw(Canvas canvas)
			{
				Log.i(tag, "onDraw");	
			//	mPaint.setColor(Color.CYAN);
				DisplayMetrics metrics = getResources().getDisplayMetrics();
				int height = canvas.getHeight();
				int width = canvas.getWidth();
			/*	mPenPaint.setColor(Color.RED);
				mPenPaint.setStrokeWidth(5);
				mPenPaint.setAntiAlias(true);
				mPenPaint.setTextSize(30);
				mPenPaint.setTextAlign(Align.CENTER); */
				//mPenPaint.setTextScaleX(0.8);
				Log.i(tag, "onDraw metrics x=" + metrics.widthPixels + " y=" + metrics.heightPixels);
				Log.i(tag, "onDraw height="+height + " width=" + width);
				
				int orientation = getResources().getConfiguration().orientation;
				int radius = Math.min(width, height)/2;				
				canvas.drawCircle(width/2, height/2, radius, mPaint);
				int stopX, stopY;
				//mHeadAngle = 315;
				double angle = (double)(PI*mHeadAngle/180.0);
				
				int signx = (int) Math.floor(Math.cos(angle) + 0.5);
				int signy = (int) Math.floor(Math.sin(angle) + 0.5);
				int line = (int)(0.9*radius);
				
				Log.i(tag, "sign x=" + signx + " y=" + signy + " angle=" + Double.toString(angle));
				if (orientation == Configuration.ORIENTATION_LANDSCAPE)
				{
					canvas.drawText("S", width/2, height, mPenPaint);
					canvas.drawText("N", width/2, (int)(0.05*height), mPenPaint);
					canvas.drawText("E", width/2+radius, height/2, mPenPaint);
					canvas.drawText("W", width/2-radius, height/2, mPenPaint);									
				}
				else
				{
					canvas.drawText("E", (int)(width/2+0.95*radius), height/2, mPenPaint);
					canvas.drawText("W", (int)(width/2-0.95*radius), height/2, mPenPaint);
					canvas.drawText("S", width/2, height/2+radius, mPenPaint);
					canvas.drawText("N", width/2, height/2-radius, mPenPaint);
					stopY = (int)(height/2-0.9*radius);
					stopX = width/2;
				}
				/*
				if (signx > 0 && signy > 0)
				{
					stopY = (int)(height/2-(Math.sin(angle)*line));
					stopX = (int)(width/2+(Math.cos(angle)*line));						
				}
				else if (signx < 0 && signy < 0)
				{
					stopY = (int)(height/2-(Math.sin(angle)*line));
					stopX = (int)(width/2+(Math.cos(angle)*line));
				} 
				else if (signx < 0 && signy > 0)
				{
					stopY = (int)(height/2+(Math.sin(angle)*line));
					stopX = (int)(width/2-(Math.cos(angle)*line));	
				} 
				else if (signy == 0)
				{			
					stopY = (int)(height/2-signx*line);
					stopX = width/2;	
				}	
				else if (signx == 0)
				{			
					stopY = (int)height/2;
					stopX = (int)(width/2+signy*line);	
				}	
				else
				{			
					stopY = (int)(height/2+(Math.sin(angle)*line));
					stopX = (int)(width/2-(Math.cos(angle)*line));	
				}			
				*/
				stopX = (int)(width/2-(Math.sin(angle)*line));
				stopY = (int)(height/2-(Math.cos(angle)*line));	
				canvas.drawLine(width/2, height/2, stopX, stopY, mPenPaint);

				
			}
		};
		
		setContentView(view);

		//Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		//sm.registerListener(orientlistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	private void init()
	{
		mPaint.setColor(Color.CYAN);
	//	DisplayMetrics metrics = getResources().getDisplayMetrics();
	//	int height = canvas.getHeight();
	//	int width = canvas.getWidth();
		mPenPaint.setColor(Color.RED);
		mPenPaint.setStrokeWidth(5);
		mPenPaint.setAntiAlias(true);
		mPenPaint.setTextSize(30);
		mPenPaint.setTextAlign(Align.CENTER);
	}
	@Override
	public void onResume()
	{
		Log.i(tag, "onResume");
		super.onResume();
		if (orientlistener == null)
		{
			orientlistener = new SensorEventListener() {			

				@Override
				public void onAccuracyChanged(Sensor sensor, int accuracy) {
					// TODO Auto-generated method stub
					if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
						Log.i(tag, "onAccuracyChanged accuracy " + accuracy);
					}
				}
	
				@Override
				public void onSensorChanged(SensorEvent event) {
					// TODO Auto-generated method stub
					if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
						float headingAngle = event.values[0];
						//float pitchAngle = event.values[1];
						Log.i(tag, "onSensorChanged direction " + headingAngle);
						mHeadAngle = headingAngle;
						
						runOnUiThread(new Runnable() {
							@Override
                            public void run() {
								view.invalidate();
							}
							
						});

						

					}
				}			
			};
			sm.registerListener(orientlistener, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
		}
	}
	
	@Override
	public void onDestroy()
	{
		Log.i(tag, "onDestroy");
		super.onDestroy();
		if (orientlistener != null)
		{
			sm.unregisterListener(orientlistener, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION));
			orientlistener = null;
		}

	}
	
	
	
}
