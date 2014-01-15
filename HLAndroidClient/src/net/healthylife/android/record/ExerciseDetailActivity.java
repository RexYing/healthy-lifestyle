package net.healthylife.android.record;

import net.healthylife.android.R;
import net.healthylife.android.R.id;
import net.healthylife.android.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * An activity representing a single Outdoor Exercise detail screen. This
 * activity is only used on handset devices. On tablet-size devices, item
 * details are presented side-by-side with a list of items in a
 * {@link ExerciseListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link ExerciseDetailFragment}.
 */
public class ExerciseDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outdoorexercise_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(
					ExerciseDetailFragment.ARG_ITEM_ID,
					getIntent().getStringExtra(
							ExerciseDetailFragment.ARG_ITEM_ID));
			ExerciseDetailFragment fragment = new ExerciseDetailFragment();
			fragment.setArguments(arguments);
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.outdoorexercise_detail_container, fragment)
//					.commit();
			getFragmentManager().beginTransaction()
					.replace(R.id.outdoorexercise_detail_container, fragment)
					.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO: add options
		return super.onOptionsItemSelected(item);
	}
	
}
