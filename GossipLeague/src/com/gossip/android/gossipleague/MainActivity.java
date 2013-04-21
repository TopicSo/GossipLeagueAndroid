package com.gossip.android.gossipleague;

import java.io.IOException;

import org.json.JSONException;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.gossip.android.gossipleague.entities.Ranking;
import com.gossip.android.gossipleague.fragments.RankingFragment;
import com.gossip.android.gossipleague.utils.UrlFetcher;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

		if (fragment == null) {
			fragment = new RankingFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment)
					.commit();
		}
	}

}
