package net.healthylife.android;

import net.healthylife.android.record.RecordNavActivity;
import net.healthylife.android.stats.StatsActivity;
import net.healthylife.android.R;
import net.healthylife.android.R.layout;
import net.healthylife.android.R.menu;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DashboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}
	
	public void onRecordBtnClicked(View view) {
		Intent recordIntent = new Intent(this, RecordNavActivity.class);
		startActivity(recordIntent);
	}
	
	public void onStatsBtnClicked(View view) {
		Intent statsIntent = new Intent(this, StatsActivity.class);
		startActivity(statsIntent);
	}

}
