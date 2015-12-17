package com.fantingame.game.mywar.logic.legion.model;

import java.util.Date;

public class UserInvestInfo {

	private String userId;

	private int investId;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getInvestId() {
		return investId;
	}

	public void setInvestId(int investId) {
		this.investId = investId;
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
