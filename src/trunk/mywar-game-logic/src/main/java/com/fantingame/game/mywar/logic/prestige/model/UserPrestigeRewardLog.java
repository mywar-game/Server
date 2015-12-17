package com.fantingame.game.mywar.logic.prestige.model;

import java.util.Date;

public class UserPrestigeRewardLog {
	private String userId;
	private int id;
	private Date createdTime;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
