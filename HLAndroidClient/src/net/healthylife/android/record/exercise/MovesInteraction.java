package net.healthylife.android.record.exercise;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.Toast;

public class MovesInteraction {
	
	private Activity mActivity;
	
	private static final String TAG = "Healthy Lifestyle";

	private static final String CLIENT_ID = "E9c55jZ6UHEHNZMaHPA7c16D7ga1Tc5n";

    private static final String REDIRECT_URI = "https://moves-api-demo.herokuapp.com/auth/moves/callback";

    private static final String ACTIVITY_SCOPE = "activity";
    //private static final String ALL_SCOPE = "location activity";
    
    public static final int REQUEST_AUTHORIZE = 1;
    
	public MovesInteraction(Activity activity) {
		mActivity = activity;
	}
    
	/**
	 * Need the Activity that calls authInApp to define its method onActivityResult
	 */
    public void authInApp() {
    	Uri uri = createAuthUri("moves", "app", "/authorize").build();
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            mActivity.startActivityForResult(intent, REQUEST_AUTHORIZE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mActivity, "Moves app not installed", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Helper method for building a valid Moves authorize uri.
     */
    private Uri.Builder createAuthUri(String scheme, String authority, String path) {
        return new Uri.Builder()
                .scheme(scheme)
                .authority(authority)
                .path(path)
                .appendQueryParameter("client_id", CLIENT_ID)
                .appendQueryParameter("redirect_uri", REDIRECT_URI)
                .appendQueryParameter("scope", ACTIVITY_SCOPE)
                .appendQueryParameter("state", String.valueOf(SystemClock.uptimeMillis()));
    }
    
}
