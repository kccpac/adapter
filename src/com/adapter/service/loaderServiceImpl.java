package com.adapter.service;

import com.adapter.service.common.ILoaderService;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class loaderServiceImpl extends Service{

	private static final String TAG = loaderServiceImpl.class.getName();

/*	private final ILoaderService.Stub mBinder = new ILoaderService.Stub() {

		@Override
		public void setup() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "setup");
		}		
	};
	*/
	
    public class LocalBinder extends Binder {
    	public loaderServiceImpl getService() {
    		Log.i(TAG, "getService tid=" + (int) Thread.currentThread().getId());
            return loaderServiceImpl.this;
        }
    }

    private final IBinder mBinder = new LocalBinder();
    
	@Override
	public void onCreate()
	{
		Log.i(TAG, "onCreate tid=" + (int) Thread.currentThread().getId());
	}
	
/*
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
	
	*/
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind");
		return mBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent)
	{
		Log.i(TAG, "onUnbind tid=" + (int) Thread.currentThread().getId());
		return false;
	}
	
	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags)
	{
		Log.i(TAG, "bindService");
		return true;
	}

	public void setup() {
		// TODO Auto-generated method stub
		Log.i(TAG, "setup");
	}

}
