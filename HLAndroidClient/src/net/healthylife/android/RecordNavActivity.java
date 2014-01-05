package net.healthylife.android;

import com.google.cloud.backend.R;
import com.google.cloud.backend.R.layout;
import com.google.cloud.backend.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RecordNavActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_nav);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record_nav, menu);
		return true;
	}

}
