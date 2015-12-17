package com.fantingame.game.mywar.logic.friend.model;

import java.util.Date;

public class UserBlackInfo {

	private int id;

	private String userId;

	private String userBlackId;

	private Date createdTime;

	private Date updatedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserBlackId() {
		return userBlackId;
	}

	public void setUserBlackId(String userBlackId) {
		this.userBlackId = userBlackId;
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
