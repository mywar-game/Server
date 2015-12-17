package com.fantingame.game.mywar.logic.legion.model;

import java.util.Date;

public class UserApplyLegionInfo {

	private String userId;

	private String legionId;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLegionId() {
		return legionId;
	}

	public void setLegionId(String legionId) {
		this.legionId = legionId;
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
