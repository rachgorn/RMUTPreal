package com.himebaugh.employeedirectory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends Activity implements OnClickListener {

	public TextView mName;
	public TextView mTitle;
	public TextView mDepartment;
	public TextView mCity;
	private TextView mOfficePhone;
	private TextView mEmail;
	private ImageView mPicture;

	private int mID;
	public Uri uri;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// implement Up Navigation with caret in front of App icon in the Action
		// Bar		


		// getting intent data
		Intent intent = getIntent();

		Uri launchData = intent.getData();
		mID = Integer.parseInt(launchData.getLastPathSegment());

		uri = ContentUris.withAppendedId(EmployeeProvider.CONTENT_URI, mID);

		// Update values on the screen with XML values from previous intent
		mName = (TextView) findViewById(R.id.activity_detail_name);
		mTitle = (TextView) findViewById(R.id.activity_detail_title);
		mDepartment = (TextView) findViewById(R.id.activity_detail_department);
		mCity = (TextView) findViewById(R.id.activity_detail_city);
		mOfficePhone = (TextView) findViewById(R.id.activity_detail_office_phone);
		mEmail = (TextView) findViewById(R.id.activity_detail_email);
		mPicture = (ImageView) findViewById(R.id.activity_detail_picture);

		// listen for button clicks
		findViewById(R.id.activity_detail_call_office_button).setOnClickListener(this);
		findViewById(R.id.activity_detail_office_phone).setOnClickListener(this);
		findViewById(R.id.activity_detail_send_email_button).setOnClickListener(this);
		findViewById(R.id.activity_detail_email).setOnClickListener(this);

		// add PhoneStateListener FROM
		// http://www.mkyong.com/android/how-to-make-a-phone-call-in-android/
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

		// Parse xml data in a non-ui thread
		new LoadEmployeesTask().execute();

	}

	private class LoadEmployeesTask extends AsyncTask<String, Void, Cursor> {

		@Override
		protected Cursor doInBackground(String... args) {

			String[] projection = { EmployeeDatabase.COLUMN_ID, EmployeeDatabase.COLUMN_FIRSTNAME,
					EmployeeDatabase.COLUMN_LASTNAME, EmployeeDatabase.COLUMN_TITLE,
					EmployeeDatabase.COLUMN_DEPARTMENT, EmployeeDatabase.COLUMN_CITY,
					EmployeeDatabase.COLUMN_OFFICE_PHONE,
					EmployeeDatabase.COLUMN_EMAIL, EmployeeDatabase.COLUMN_PICTURE };
			// String selection = "_id = 2";
			String selection = null;
			String[] selectionArgs = null;
			String sortOrder = null;

			Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

			return cursor;
		}

		@Override
		protected void onPostExecute(Cursor cursor) {

			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();

				mName.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_FIRSTNAME)));
				mName.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_LASTNAME)));

				mTitle.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_TITLE)));
				mDepartment.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_DEPARTMENT)));
				mCity.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_CITY)));
				mOfficePhone.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_OFFICE_PHONE)));
				mEmail.setText(cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_EMAIL)));

				InputStream is;
				try {
					is = getAssets().open(
							"pics/" + cursor.getString(cursor.getColumnIndex(EmployeeDatabase.COLUMN_PICTURE)));
					Bitmap bit = BitmapFactory.decodeStream(is);
					mPicture.setImageBitmap(bit);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			final GifMovieView gif1 = (GifMovieView) findViewById(R.id.gifMovieView);
			gif1.setMovieResource(R.drawable.click);
		}


	}
	public void finishActivity(View v){
		finish();
	}



	// @Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.activity_detail_call_office_button:
			case R.id.activity_detail_office_phone: // clicking on textView will
				// have same action as the
				// button

				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + mOfficePhone.getText().toString()));
				startActivity(callIntent);
				break;


			case R.id.activity_detail_send_email_button:
			case R.id.activity_detail_email: // clicking on textView will have same
				// action as the button

				String to = mEmail.getText().toString();
				String subject = "textSubject";
				String message = "textMessage";

				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
				email.putExtra(Intent.EXTRA_SUBJECT, subject);
				email.putExtra(Intent.EXTRA_TEXT, message);

				// prompts email apps
				email.setType("message/rfc822");

				startActivity(Intent.createChooser(email, "Choose an Email client :"));
				break;

		}

	}

	// monitor phone call activities
	private class PhoneCallListener extends PhoneStateListener {

		private boolean isPhoneCalling = false;

		String LOG_TAG = "LANGDON";

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}

			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");

				isPhoneCalling = true;
			}

			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// run when class initial and phone call ended,
				// need detect flag from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");

			}
		}

	}

}