package com.adapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adapter.provider.dataContract;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class One extends ActionBarActivity {

	public static final String tag = One.class.getCanonicalName();

	private static final int READ_REQUEST_CODE = 42;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;

	private ImageView loadImage;
	private ImageView captureImage;
	private Button btnSubmit;
	private TextView imageURL;

	private TextView imageTitle;

	private TextView imageDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		imageURL = (TextView) findViewById(R.id.TextLine4);
		imageTitle = (TextView) findViewById(R.id.editText1);
		imageDescription = (TextView) findViewById(R.id.editText2);
		loadImage = (ImageView) findViewById(R.id.imageView1);
		loadImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(tag, "onClick loadImage from device");

				Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

				// Filter to only show results that can be "opened", such as a
				// file (as opposed to a list of contacts or timezones)
				intent.addCategory(Intent.CATEGORY_OPENABLE);

				// Filter to show only images, using the image MIME data type.
				// If one wanted to search for ogg vorbis files, the type would
				// be "audio/ogg".
				// To search for all documents available via installed storage
				// providers,
				// it would be "*/*".
				intent.setType("image/*");

				startActivityForResult(intent, READ_REQUEST_CODE);
			}
		});

		captureImage = (ImageView) findViewById(R.id.imageView2);
		captureImage.setOnClickListener(new OnClickListener() {
			Uri getOutputMediaFileUri(int type) {
				return Uri.fromFile(getOutputMediaFile(type));
			}

			/** Create a File for saving an image or video */
			File getOutputMediaFile(int type) {
				// To be safe, you should check that the SDCard is mounted
				// using Environment.getExternalStorageState() before doing
				// this.

				File mediaStorageDir = new File(
						Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
						"adapter");
				// This location works best if you want the created images to be
				// shared
				// between applications and persist after your app has been
				// uninstalled.

				// Create the storage directory if it does not exist
				if (mediaStorageDir.exists() == false) {
					if (mediaStorageDir.mkdirs() == false) {
						Log.d(tag, "failed to create directory");
						return null;
					}
				}

				// Create a media file name
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
						.format(new Date());
				File mediaFile;
				if (type == MEDIA_TYPE_IMAGE) {
					mediaFile = new File(mediaStorageDir.getPath()
							+ File.separator + "IMG_" + timeStamp + ".jpg");
				} else {
					return null;
				}

				return mediaFile;
			}

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri fileUri;

				Log.i(tag, "captureImage onClick");

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image	file name
				imageURL.setText(fileUri.toString());	
				
				// start the image capture Intent
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

			}

		});

		btnSubmit = (Button) findViewById(R.id.button1);

		btnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(tag, "Submit onClick");
				ContentResolver resolver = getApplicationContext().getContentResolver();

				ContentValues values = new ContentValues();
				
				values.put("url", imageURL.getText().toString());
				values.put("title", imageTitle.getText().toString());
				values.put("description", imageDescription.getText().toString());
				resolver.insert(dataContract.IMAGEINFO_URI, values);
				
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);	
				finish();

			}
		});

	}

	@Override
	public void onResume() {
		Log.i(tag, "onResume Enter");
		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent result) {
		Log.i(tag, "onActivityResult Enter");

		if (resultCode != RESULT_OK)
			return;

		if (requestCode == READ_REQUEST_CODE) {
			Log.i(tag, "loadImage");
			Uri uri = null;
			if (result != null) {
				uri = result.getData();
				Log.i(tag, "Uri: " + uri.toString());
				imageURL.setText(uri.toString());
				imageURL.setVisibility(View.VISIBLE);

			}
		} else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			Log.i(tag, "captureImage");
			// Uri uri = null;
			// if (result != null)
			{
				// uri = result.getData();
				Log.i(tag, "Uri: " + imageURL.getText().toString());
				// imageURL.setText(uri.getPath());
				imageURL.setVisibility(View.VISIBLE);
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.one, menu);
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
