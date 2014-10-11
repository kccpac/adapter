package com.adapter;

import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.adapter.service.common.IAlarmService;
import com.adapter.service.common.IAlarmServiceCallback;
import com.adapter.service.common.ILoaderService;
import com.adapter.service.loaderServiceImpl;
//import com.test.service.loaderServiceImpl.LocalBinder;
//import com.test.service.common.loaderServiceImpl.LocalBinder;

public class Three extends ActionBarActivity {

	private final String TAG = Three.class.getName();
	private loaderServiceImpl loaderService = null;
	private IAlarmService alarmService = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.activity_three);
		mIsBound = false;

	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
        public void onServiceConnected(ComponentName className,
                IBinder iservice) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  We are communicating with our
            // service through an IDL interface, so get a client-side
            // representation of that from the raw service object.
			Log.i(TAG, 	"onServiceConnected name= " + className.getClassName() + 
						" tid=" + (int) Thread.currentThread().getId());
			
			alarmService = IAlarmService.Stub.asInterface(iservice);
			
			try {				
				alarmService.registerAlarmCallback(mIAlarmServiceCallback);
				alarmService.setup();
				mIsBound = true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//loaderService = ILoaderService.Stub.asInterface(service);
			
			//LocalBinder binder = (LocalBinder) service;
			//loaderService = binder.getService();

            // We want to monitor the service for as long as we are
            // connected to it.
          /*  try {
            //	loaderService.registerCallback(mCallback);
            } catch (RemoteException e) {
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            } */
        }

		@Override
        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
			Log.i(TAG, "onServiceDisconnected");
			loaderService = null;
			alarmService = null;
			mIsBound = false;

            // As part of the sample, tell the user what happened.
            //Toast.makeText(Three.class., "onServiceDisconnected", Toast.LENGTH_SHORT).show();
        }
    };
    

	private boolean mIsBound;
	
	@Override 
    public void onResume()
    {
    	Log.i(TAG, "onResume");
		super.onResume();
    	
    	if (mIsBound == false)
    	{
    		Log.i(TAG, "Lanuch");
    		Intent intent = new Intent(IAlarmService.class.getName());
    		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    	}
    		

    }
    
    @Override
    public void onPause()
    {
    	Log.i(TAG, "onPause");
    	if (mIsBound == true) {
			Log.i(TAG, "unbindService");			
			unbindService(mConnection);
			alarmService= null;
			mIsBound = false;
		}
    	super.onPause();
    }
    
    @Override 
    public void onDestroy()
    {
    	Log.i(TAG, "onDestroy");
  /*  	if (loaderService != null)
    	{
    		unbindService(mConnection);
    		loaderService = null;    		
    	}    	
    */	
    	super.onDestroy();
    }

	private final IAlarmServiceCallback mIAlarmServiceCallback = new IAlarmServiceCallback.Stub() {
		@Override
		public void wakeup() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "wakeup");
			
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.three, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
