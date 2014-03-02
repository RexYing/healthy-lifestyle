package net.healthylife.android.record;

import net.healthylife.android.R;
import net.healthylife.android.R.layout;
import net.healthylife.android.R.menu;
import net.healthylife.android.record.exercise.ExerciseListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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
	
	public void onOutdoorBtnClicked(View view) {
		Intent exerciseIntent = new Intent(this, ExerciseListActivity.class);
		startActivity(exerciseIntent);
	}

}
