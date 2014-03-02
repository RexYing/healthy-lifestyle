package net.healthylife.android.stats;

import net.healthylife.android.R;
import net.healthylife.android.R.layout;
import net.healthylife.android.R.menu;
import net.healthylife.android.utils.SmartFragmentStatePagerAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

public class StatsActivity extends Activity {
	
	StatsPagerAdapter mStatsPagerAdapter;
    ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		// Show the Up button in the action bar.
		setupActionBar();
		
		mStatsPagerAdapter =
                new StatsPagerAdapter(
                        getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mStatsPagerAdapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Page adaptor for ViewPager
	 * @author Rex
	 *
	 */
	public class StatsPagerAdapter extends SmartFragmentStatePagerAdapter {
		private int numItems = 2;
		
	    public StatsPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }

	    @Override
	    public Fragment getItem(int ind) {
            switch (ind) {
            	case 0: // Fragment # 0 - This will show FirstFragment
            		return DailyFragment.newInstance(0, "Page # 1");
            	case 1: // Fragment # 0 - This will show FirstFragment different title
            		return DailyFragment.newInstance(1, "Page # 2");
            	default:
            		return null;
            }
	    }

	    @Override
	    public int getCount() {
	        return numItems;
	    }

	    @Override
	    public CharSequence getPageTitle(int position) {
	        return "Page " + (position + 1);
	    }
	}

}
