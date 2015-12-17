package com.fantingame.game.mywar.logic.hero.model;

import java.util.Date;

public class UserCareerInfo {

	private String userId;

	private int detailedCareerId;

	private int level;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getDetailedCareerId() {
		return detailedCareerId;
	}

	public void setDetailedCareerId(int detailedCareerId) {
		this.detailedCareerId = detailedCareerId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
