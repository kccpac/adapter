package com.adapter.service;

//import android.app.IntentService;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.adapter.service.common.IAlarmService;
import com.adapter.service.common.IAlarmServiceCallback;

public class alarmService extends Service{

	private static final String TAG = alarmService.class.getName();

	private AlarmManager alarmManager;

	private  IAlarmServiceCallback mCallback;
	private final IAlarmService.Stub mBinder = new IAlarmService.Stub() {

			@Override
			public void setup() throws RemoteException {
				// TODO Auto-generated method stub
				Log.i(TAG, "setup tid=" + (int) Thread.currentThread().getId());	
				init();				
			}	

			@Override
			public void registerAlarmCallback(IAlarmServiceCallback cb)
					throws RemoteException {
				// TODO Auto-generated method stub
				Log.i(TAG, "registerAlarmCallback tid=" + (int) Thread.currentThread().getId());	
				mCallback = cb;
			}
		};
		
	public alarmService() {
		Log.i(TAG, "alarmService");	
		
		// TODO Auto-generated constructor stub
	}	
	
	public void init()
	{
		Log.i(TAG, "init tid=" + (int) Thread.currentThread().getId());
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		try {
			mCallback.wakeup();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Intent i=new Intent(getBaseContext(), OnAlarmReceiver.class);
//		PendingIntent pi=PendingIntent.getBroadcast(getBaseContext(), 0, i, 0);

//		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), PERIOD, pi);
	}

	@Override 
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i(TAG, "onStartCommand " + flags);
		init();
		//return super.onStartCommand(intent, flags, startId);		
		return START_STICKY;

	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.i(TAG, "onCreate tid=" + (int) Thread.currentThread().getId());
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "onStart");
		init();
	}
	
	public void onServiceConnected(ComponentName name, IBinder service)
	{
		Log.i(TAG, "onServiceConnected");
		//this.binder = ILoaderService.Stub.asInterface(service);
	}
	
	public void onServiceDisconnected(ComponentName name)
	{
		Log.i(TAG, "onServiceDisconnected");
		//binder = null;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind");
//		android.os.Debug.waitForDebugger();
		return mBinder;
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	//	mCtx = this;
	}


}
