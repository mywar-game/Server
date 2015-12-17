package com.fantingame.game.mywar.logic.setting.model;

import java.util.Date;

public class UserSettingInfo {

	private String userId;

	private int displayNum;

	private String dailyTips;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDailyTips() {
		return dailyTips;
	}

	public void setDailyTips(String dailyTips) {
		this.dailyTips = dailyTips;
	}

	public int getDisplayNum() {
		return displayNum;
	}

	public void setDisplayNum(int displayNum) {
		this.displayNum = displayNum;
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
