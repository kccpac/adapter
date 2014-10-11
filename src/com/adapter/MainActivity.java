package com.adapter;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	public class ActivityObject {
		private String name;
		private String class_name;
		ActivityObject(String name, String class_name)
		{
			this.name = name;
			this.class_name = class_name;
		}
		public String getclassname() { return class_name; }
		public String getname() { return name; }

	}
	private static int TTS_DATA_CHECK = 1;
	private TextToSpeech ttsEngine = null;
	private final String tag = MainActivity.class.getName();
	boolean mTTSIsInit = false; 
	public final ActivityObject subActMap[] = { 
			new ActivityObject(One.class.getSimpleName(), One.class.getCanonicalName() ),
			new ActivityObject(Two.class.getSimpleName(), Two.class.getCanonicalName()),
			new ActivityObject(Three.class.getSimpleName(), Three.class.getCanonicalName())
			};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent (TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(intent, requestCode.INTENT_TTS_DATA_CHECK.getVal());	
		ttsEngine = new TextToSpeech(this, new OnInitListener()	{
			public void onInit(int status)
			{				
				if (status == TextToSpeech.SUCCESS)
				{
					mTTSIsInit = true;
				}				
				Log.i(tag, "TextToSpeech Engine initialization succeed = " + mTTSIsInit);
			}
		});		

		setContentView(R.layout.activity_main);

		ListView view = (ListView) findViewById(R.id.listView1);
		String actName[] = new String[subActMap.length];
		 
		for (int i=0; i<subActMap.length; i++)
		{
			actName[i] = subActMap[i].getname();
		}		

		ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1, actName);

		view.setAdapter(adapter);		
		
		view.setOnItemClickListener (new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Log.i(tag, "click" + parent.getItemAtPosition(position).toString());
				Intent intent = new Intent();
				intent.setClassName(getApplicationContext(), subActMap[position].getclassname());
				ttsEngine.speak("Select activity " + subActMap[position].getname(), TextToSpeech.QUEUE_FLUSH, null);
				startActivityForResult(intent, requestCode.INTENT_ACTIVITY_ONE.getVal());				
			}			
		});
	}

	@Override
	public void onActivityResult(int request_code, int result_code, Intent data)
	{
		Log.i(tag, "onActivityResult");
		if (request_code == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS && result_code == TextToSpeech.SUCCESS)
		{
			Log.i(tag, "TextToSpeech Engine is available.");
		}
		if (result_code == RESULT_OK)
		{
			if (request_code == requestCode.INTENT_ACTIVITY_ONE.getVal())
			{
				Log.i(tag, "INTENT_ACTIVITY_ONE succeed");
			} 
			else if (request_code == requestCode.INTENT_ACTIVITY_TWO.getVal())
			{
				Log.i(tag, "INTENT_ACTIVITY_TWO succeed");
			}
			else if (request_code == requestCode.INTENT_ACTIVITY_THREE.getVal())
			{
				Log.i(tag, "INTENT_ACTIVITY_THREE succeed");
			}
		}
		return;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
