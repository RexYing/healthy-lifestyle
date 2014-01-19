package net.healthylife.android.record.exercise;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.healthylife.android.utils.Oauth2Helper;
import net.healthylife.android.utils.Oauth2Params;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class MovesInteraction {
	
	public static final String TAG = "HealthyLife";

	private static final String CLIENT_ID = "E9c55jZ6UHEHNZMaHPA7c16D7ga1Tc5n";
	
	private static final String CLIENT_SECRET = "H4ub23lEr08x0TvknMtrke788VrK0x6lF1iPPv4792LP0O5KEy1ICP5W10fbDm_8";

    private static final String REDIRECT_URI = "https://moves-api-demo.herokuapp.com/auth/moves/callback";
    private static final String ACCESS_TOKEN_URL = "https://api.moves-app.com/oauth/v1/access_token";
    // for android
    private static final String AUTHORIZE_URI = "moves://app/authorize";
    private static final String API_URL = "https://api.moves-app.com/api/v1";
    
    private static final String ACTIVITY_SCOPE = "activity";
    //private static final String ALL_SCOPE = "location activity";
    
    private static final String USER_ID = "Moves";
    
    public static final int REQUEST_AUTHORIZE = 1;
    
    private Activity mActivity;
    private Fragment mFragment;
    private Oauth2Params mParams;
    private Oauth2Helper mOauth2Helper;
    private SharedPreferences mStore;
    
	public MovesInteraction(Fragment fragment) {
		mFragment = fragment;
		mActivity = fragment.getActivity();
		init();
	}
	
	public MovesInteraction(Activity activity) {
		mActivity = activity;
		init();
	}
	
	private void init() {
		constructParams();
		mStore = PreferenceManager.getDefaultSharedPreferences(mActivity);
		mOauth2Helper = new Oauth2Helper(mStore, mParams);
		mOauth2Helper.setTag(TAG);
	}
	
	private void constructParams() {
		mParams = new Oauth2Params(CLIENT_ID, CLIENT_SECRET,
				ACCESS_TOKEN_URL,
				AUTHORIZE_URI,
				null, // default BearerToken
				ACTIVITY_SCOPE,
				REDIRECT_URI,
				USER_ID,
				API_URL
				);
	}
	
	/**
	 * This method authorizes through app instead of URL (which requires a webview)
	 * Need the subclass of Activity/Fragment that calls authInApp to override the
	 * method onActivityResult.
	 * 
	 */
    public void authorizeInApp() {
    	
    	Uri uri = createAuthUri("moves", "app", "/authorize").build();
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
        	if (mFragment != null)
        		mFragment.startActivityForResult(intent, REQUEST_AUTHORIZE);
        	else {
				mActivity.startActivityForResult(intent, REQUEST_AUTHORIZE);
			}
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mActivity, "Moves app not installed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Helper method for building a valid Moves authorize uri (Android)
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
    
    /**
     * Access token stored in a SharedPreferences that is passed to Oauth2Params.
     * The key is USER_ID.
     * @param uri callback auth URI that contains parameter "code"
     */
    public void obtainAccessToken(Uri uri) {
		try {
			String authCode = uri.getQueryParameter("code");
			Log.i(TAG, "Found code = " + authCode);
			mOauth2Helper.retrieveAndStoreAccessToken(authCode);
		}
		catch (NullPointerException e) {
			
		}
		catch (IOException e) {
			Log.e(TAG, "Cannot retrive access token " + uri.toString(), e);
			e.printStackTrace();
		}
	}
    
    /**
     * get today's date in yyyyMMdd format
     * @return date in yyyyMMdd format
     */
    public String getTodayDate() {
    	Date dt = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	return format.format(dt);
    }
    
    /**
     * check if token is valid by and api call to get profile
     * @return if access token is valid or not
     */
    public boolean tokenIsValid() {
    	try {
			mOauth2Helper.executeApiCallWithParameters("/user/profile");
			Log.i(TAG, "Access token valid");
		} catch (IOException e) {
			Log.e(TAG, "Access token not valid");
			return false;
		}
    	return true;
    }
}
