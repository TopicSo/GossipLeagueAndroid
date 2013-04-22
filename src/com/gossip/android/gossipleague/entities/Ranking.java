package com.gossip.android.gossipleague.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Ranking {
	private static final String TAG = "GossipLeague - RankingEntity";

	private Player[] mPlayers;

	public Ranking(String response) throws JSONException {
		fromJSON(response);
	}

	public Player[] getPlayers() {
		return mPlayers;
	}

	public void setPlayers(Player[] players) {
		mPlayers = players;
	}

	public void fromJSON(String the_json) throws JSONException {

		JSONObject myjson;
		try {

			myjson = new JSONObject(the_json);
			if (the_json != null) {
				JSONArray playersArrayJSON = myjson.getJSONArray("players");
				mPlayers = new Player[playersArrayJSON.length()];

				for (int i = 0; i < playersArrayJSON.length(); i++) {
					JSONObject player = (JSONObject) playersArrayJSON.get(i);
					Log.i(TAG,player.getString("username"));
					mPlayers[i] = new Player(player.getString("id"),
							player.getString("username"),
							player.getString("avatar"),
							(float) player.getDouble("score"),
							player.getInt("countGames"),
							player.getInt("countWins"),
							player.getInt("countLosts"),
							player.getInt("countDraws"),
							player.getInt("countScoredGoals"),
							player.getInt("countConcededGoals"));
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, e.getLocalizedMessage());
		}
	}
}
