package net.healthylife.android.record.exercise;

import net.healthylife.android.R;
import net.healthylife.android.R.id;
import net.healthylife.android.R.layout;
import net.healthylife.android.record.exercise.ExerciseListFragment.Callbacks;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * An activity representing a list of Outdoor Exercises. This activity has
 * different presentations for handset and tablet-size devices. On handsets, the
 * activity presents a list of items, which when touched, lead to a
 * {@link ExerciseDetailActivity} representing item details. On tablets,
 * the activity presents the list of items and item details side-by-side using
 * two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ExerciseListFragment} and the item details (if present) is a
 * {@link ExerciseDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link ExerciseListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class ExerciseListActivity extends Activity implements
		ExerciseListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outdoorexercise_list);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		if (findViewById(R.id.outdoorexercise_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			
//			((ExerciseListFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.outdoorexercise_list))
//                    .setActivateOnItemClick(true);
			ExerciseListFragment fragment = (ExerciseListFragment) getFragmentManager()
					.findFragmentById(R.id.outdoorexercise_list);
			fragment.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//TODO: add options
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Callback method from {@link ExerciseListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ExerciseDetailFragment.ARG_ITEM_ID, id);
			ExerciseDetailFragment fragment = new ExerciseDetailFragment();
			fragment.setArguments(arguments);
//			getSupportFragmentManager().beginTransaction()
//					.replace(R.id.outdoorexercise_detail_container, fragment)
//					.commit();
			getFragmentManager().beginTransaction()
					.replace(R.id.outdoorexercise_detail_container, fragment)
					.commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this,
					ExerciseDetailActivity.class);
			detailIntent
					.putExtra(ExerciseDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
