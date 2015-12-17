package com.fantingame.game.mywar.logic.task.model;

import java.util.Date;

public class UserDailyTaskInfo {

	private String userId;

	private int times;

	private int systemTaskId;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getSystemTaskId() {
		return systemTaskId;
	}

	public void setSystemTaskId(int systemTaskId) {
		this.systemTaskId = systemTaskId;
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
