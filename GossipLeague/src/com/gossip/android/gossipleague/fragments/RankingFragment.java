package com.gossip.android.gossipleague.fragments;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gossip.android.gossipleague.R;
import com.gossip.android.gossipleague.entities.Player;
import com.gossip.android.gossipleague.entities.Ranking;
import com.gossip.android.gossipleague.utils.UrlFetcher;

public class RankingFragment extends Fragment {

	private static final String TAG = "GossipLeague - RankingFragment";

	private Ranking mRanking;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		new FetchRanking().execute();
		View v = inflater.inflate(R.layout.fragment_ranking, parent, false);
		return v;
	}

	// dummy function, erase in the future
	private void fillInList() {
		final ListView listview = (ListView) getView().findViewById(
				R.id.rankingList);

		Player[] players = mRanking.getPlayers();
		String[] values = new String[players.length];

		for (int i = 0; i < players.length; i++) {
			values[i] = players[i].getUsername();
		}

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
	}

	private class FetchRanking extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				String result = new UrlFetcher()
						.getUrl("http://gossip-league-api.herokuapp.com/players/ranking");
				Log.i(TAG, result);
				mRanking = new Ranking(result);
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						fillInList();
					}
				});
				
			} catch (IOException ioe) {
				Log.e(TAG, "Failed");
			} catch (JSONException e) {
				Log.d(TAG, e.getLocalizedMessage());
			}
			return null;
		}
	}
}
