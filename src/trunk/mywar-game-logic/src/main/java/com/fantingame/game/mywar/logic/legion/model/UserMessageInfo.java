package com.fantingame.game.mywar.logic.legion.model;

import java.util.Date;

public class UserMessageInfo {

	private String legionId;

	private String userId;

	private String message;

	private Date createdTime;

	private Date updatedTime;

	public String getLegionId() {
		return legionId;
	}

	public void setLegionId(String legionId) {
		this.legionId = legionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
