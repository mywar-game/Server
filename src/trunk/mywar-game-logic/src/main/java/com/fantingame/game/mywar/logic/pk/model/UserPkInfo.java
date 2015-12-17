package com.fantingame.game.mywar.logic.pk.model;

import java.util.Date;

/**
 * 用户竞技场信息
 * 
 * @author yezp
 */
public class UserPkInfo {

	private String userId;

	private int rank;
	
	/**
	 * 最高历史排名
	 */
	private int highestRank;

	/**
	 * 今日挑战次数
	 */
	private int hasChallengeTimes;

	/**
	 * 0 未重置 1 已重置
	 */
	private int isReset;

	/**
	 *   购买挑战次数
	 */
	private int buyChallengeTimes;
	
	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getBuyChallengeTimes() {
		return buyChallengeTimes;
	}

	public void setBuyChallengeTimes(int buyChallengeTimes) {
		this.buyChallengeTimes = buyChallengeTimes;
	}

	public int getHighestRank() {
		return highestRank;
	}

	public void setHighestRank(int highestRank) {
		this.highestRank = highestRank;
	}

	public int getHasChallengeTimes() {
		return hasChallengeTimes;
	}

	public void setHasChallengeTimes(int hasChallengeTimes) {
		this.hasChallengeTimes = hasChallengeTimes;
	}

	public int getIsReset() {
		return isReset;
	}

	public void setIsReset(int isReset) {
		this.isReset = isReset;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
