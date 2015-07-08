package com.himebaugh.employeedirectory;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SearchableActivity extends ListActivity {

	// SEE THIS
	// http://code.google.com/p/libs-for-android/source/browse/demos/jamendo/src/com/google/android/demos/jamendo/app/SearchActivity.java?r=6b555f06dd141fd1951c17c701c7555a9052c5d0

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// implement Up Navigation with caret in front of App icon in the Action Bar


		Intent intent = getIntent();
		checkIntent(intent);
	}



	private void checkIntent(Intent intent) {

			Uri details = intent.getData();
			Intent detailsIntent = new Intent(Intent.ACTION_VIEW, details);
			startActivity(detailsIntent);



	}





}
