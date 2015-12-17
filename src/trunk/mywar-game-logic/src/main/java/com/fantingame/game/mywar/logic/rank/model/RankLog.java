package com.fantingame.game.mywar.logic.rank.model;

import java.util.Date;

public class RankLog {

	private String rankKey;

	private String date;

	private String rankData;

	private Date createdTime;

	public String getRankKey() {
		return rankKey;
	}

	public void setRankKey(String rankKey) {
		this.rankKey = rankKey;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRankData() {
		return rankData;
	}

	public void setRankData(String rankData) {
		this.rankData = rankData;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
