package com.fantingame.game.mywar.logic.life.model;

import java.util.Date;

public class UserHangupInfo {

	private String userId;

	private int category;

	private String userHeroIdI;

	private String userHeroIdIi;

	private String userHeroIdIii;

	private String userFriendId;

	private Date createdTime;

	private Date updatedTime;
	
	// 记录当前收益
	private String rewards;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getUserHeroIdI() {
		return userHeroIdI;
	}

	public void setUserHeroIdI(String userHeroIdI) {
		this.userHeroIdI = userHeroIdI;
	}

	public String getUserHeroIdIi() {
		return userHeroIdIi;
	}

	public void setUserHeroIdIi(String userHeroIdIi) {
		this.userHeroIdIi = userHeroIdIi;
	}

	public String getUserHeroIdIii() {
		return userHeroIdIii;
	}

	public void setUserHeroIdIii(String userHeroIdIii) {
		this.userHeroIdIii = userHeroIdIii;
	}

	public String getUserFriendId() {
		return userFriendId;
	}

	public void setUserFriendId(String userFriendId) {
		this.userFriendId = userFriendId;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
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
