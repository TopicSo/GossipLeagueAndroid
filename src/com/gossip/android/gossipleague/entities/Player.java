package com.gossip.android.gossipleague.entities;

public class Player {
	private String mID;
	private String mUsername;
	private String mAvatar;
	private float mScore;
	private int mCountGames;
	private int mCountWins;
	private int mCountLosts;
	private int mCountDraws;
	private int mCountScoredGoals;
	private int mConcededGoals;

	public Player(String iD, String username, String avatar, float score,
			int countGames, int countWins, int countLosts, int countDraws,
			int countScoredGoals, int concededGoals) {
		super();
		mID = iD;
		mUsername = username;
		mAvatar = avatar;
		mScore = score;
		mCountGames = countGames;
		mCountWins = countWins;
		mCountLosts = countLosts;
		mCountDraws = countDraws;
		mCountScoredGoals = countScoredGoals;
		mConcededGoals = concededGoals;
	}

	public String getID() {
		return mID;
	}

	public void setID(String iD) {
		mID = iD;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getAvatar() {
		return mAvatar;
	}

	public void setAvatar(String avatar) {
		mAvatar = avatar;
	}

	public float getScore() {
		return mScore;
	}

	public void setScore(float score) {
		mScore = score;
	}

	public int getCountGames() {
		return mCountGames;
	}

	public void setCountGames(int countGames) {
		mCountGames = countGames;
	}

	public int getCountWins() {
		return mCountWins;
	}

	public void setCountWins(int countWins) {
		mCountWins = countWins;
	}

	public int getCountLosts() {
		return mCountLosts;
	}

	public void setCountLosts(int countLosts) {
		mCountLosts = countLosts;
	}

	public int getCountDraws() {
		return mCountDraws;
	}

	public void setCountDraws(int countDraws) {
		mCountDraws = countDraws;
	}

	public int getCountScoredGoals() {
		return mCountScoredGoals;
	}

	public void setCountScoredGoals(int countScoredGoals) {
		mCountScoredGoals = countScoredGoals;
	}

	public int getConcededGoals() {
		return mConcededGoals;
	}

	public void setConcededGoals(int concededGoals) {
		mConcededGoals = concededGoals;
	}

}
