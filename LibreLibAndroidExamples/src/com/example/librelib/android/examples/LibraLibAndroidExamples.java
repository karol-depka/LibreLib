package com.example.librelib.android.examples;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LibraLibAndroidExamples extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_libra_lib_android_examples);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.libra_lib_android_examples, menu);
		return true;
	}

}
