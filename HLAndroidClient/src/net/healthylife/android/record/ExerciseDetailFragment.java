package net.healthylife.android.record;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import net.healthylife.android.R;
import net.healthylife.android.record.exercise.ExerciseContent;
import net.healthylife.android.record.exercise.MovesInteraction;

/**
 * A fragment representing a single Outdoor Exercise detail screen. This
 * fragment is either contained in a {@link ExerciseListActivity} in
 * two-pane mode (on tablets) or a {@link ExerciseDetailActivity} on
 * handsets.
 */
public class ExerciseDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	static final String FROM_MOVES_APP_ID = "from_moves";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private ExerciseContent.ExerciseItem mItem;
	
	private MovesInteraction mMovesInteraction;
	
	private boolean mLoggedIn = false;
	
	private boolean mNeedResume = false;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ExerciseDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = ExerciseContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
		mMovesInteraction = new MovesInteraction(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView;

		if (mItem.id.equals(FROM_MOVES_APP_ID)) {
			// get walking/running/cycling info from Moves App
			rootView = inflater.inflate(R.layout.fragment_from_moves,
					container, false);
			new VerifyToken().execute();
		} else {
			rootView = inflater.inflate(
					R.layout.fragment_outdoorexercise_detail, container, false);
			// Show the dummy content as text in a TextView.
			if (mItem != null) {
				((TextView) rootView.findViewById(R.id.outdoorexercise_detail))
						.setText(mItem.content);
			}
		}
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (mNeedResume) {
			if (!mLoggedIn)
				new VerifyToken().execute();
		}
		else {
			mNeedResume = true;
		}
	}
	
	private void oauthMoves() {	
		mMovesInteraction.authorizeInApp();
	}
	
    /**
     * Handle the result from Moves authorization flow. The result is delivered as an uri documented
     * on the developer docs (see link below).
     *
     * @see https://dev.moves-app.com/docs/api
     */
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

    	super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MovesInteraction.REQUEST_AUTHORIZE:
            	// response URI
                Uri resultUri = data.getData();
                String msg;
                if (resultCode == Activity.RESULT_OK) {
                	msg = "Authorized successfully!";
                	new ProcessToken(resultUri).execute();
                }
                else {
					msg = "Failed to authorize: " + resultUri.getQueryParameter("error");
				}
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                break;
            default:
            	Toast.makeText(getActivity(), "Unrecognized response from Moves",
            			Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * Check validity of access token. If there is one, no need to authorize
     * If token is not valid, it will then call oauthMoves to start oauth2 process
     */
    private class VerifyToken extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			return mMovesInteraction.tokenIsValid();
		}
    	
		protected void onPostExecute(Boolean isValid) {
			if (!isValid)
				oauthMoves();
		}
    }
    
	private class ProcessToken extends AsyncTask<Uri, Void, Void> {

		Uri uri;

		public ProcessToken(Uri uri) {
			this.uri = uri;
		}
		
		@Override
		protected Void doInBackground(Uri...params) {
			mMovesInteraction.obtainAccessToken(uri);
  		    mLoggedIn = true;
  		    return null;
		}

		/**
		 * When we're done and we've retrieved either a valid token or an error from the server.
		 * Can start retrieving data in stats
		 */
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}
}
