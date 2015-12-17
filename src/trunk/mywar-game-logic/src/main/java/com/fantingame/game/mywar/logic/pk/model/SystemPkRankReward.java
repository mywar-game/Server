package com.fantingame.game.mywar.logic.pk.model;

/**
 * 竞技场奖励
 * 
 * @author yezp
 */
public class SystemPkRankReward {

	private int type;

	private int minRank;

	private int maxRank;

	private String rewards;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMinRank() {
		return minRank;
	}

	public void setMinRank(int minRank) {
		this.minRank = minRank;
	}

	public int getMaxRank() {
		return maxRank;
	}

	public void setMaxRank(int maxRank) {
		this.maxRank = maxRank;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

}
