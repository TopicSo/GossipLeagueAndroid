package com.gossip.android.gossipleague.fragments;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

	private ArrayList<String> mList;
	private ArrayAdapter<String> mAdapter;
	private Menu mMenu;
	private View mPlayersListView;
	private View mLoadingView;

	private Ranking mRanking;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		new FetchRanking().execute();
		View v = inflater.inflate(R.layout.fragment_ranking, parent, false);
		mPlayersListView = v.findViewById(R.id.rankingList);
		mLoadingView = v.findViewById(R.id.loading_status);
		showProgress(true);
		return v;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.activity_main, menu);
		mMenu = menu;
		mMenu.getItem(0).setEnabled(false);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			showProgress(true);
			mMenu.getItem(0).setEnabled(false);
			removeList();
			refresh();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void refresh() {
		new FetchRanking().execute();
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

		mList = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			mList.add(values[i]);
		}
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mList);
		listview.setAdapter(mAdapter);
	}

	private void removeList() {
		for (int i = 0; i < mList.size(); i++) {
			mList.remove(i);
			mAdapter.notifyDataSetChanged();
		}
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
						showProgress(false);
						mMenu.getItem(0).setEnabled(true);
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

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoadingView.setVisibility(View.VISIBLE);
			mLoadingView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoadingView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mPlayersListView.setVisibility(View.VISIBLE);
			mPlayersListView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mPlayersListView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
			mPlayersListView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
