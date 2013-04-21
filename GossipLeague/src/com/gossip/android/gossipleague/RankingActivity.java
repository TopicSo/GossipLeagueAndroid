package com.gossip.android.gossipleague;

import java.io.IOException;

import org.json.JSONException;

import com.gossip.android.gossipleague.entities.Ranking;
import com.gossip.android.gossipleague.utils.UrlFetcher;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class RankingActivity extends Activity {
	
	private static final String TAG = "GossipLeague - RankingActivity";

	private Ranking mRanking;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		new FetchRanking().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ranking, menu);
		return true;
	}
	
	private class FetchRanking extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				String result = new UrlFetcher().getUrl("http://gossip-league-api.herokuapp.com/players/ranking");
				mRanking = new Ranking(result);
			} catch (IOException ioe) {
				Log.e(TAG, "Failed");
			} catch (JSONException e) {
				Log.d(TAG, e.getLocalizedMessage());
			}
			return null;
		}
	}

}
