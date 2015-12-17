package com.fantingame.game.mywar.logic.explore.model;

import java.util.Date;

/**
 * 用户探索信息
 * 
 * @author yezp
 */
public class UserExploreInfo {

	private String userId;

	private int integral;

	private int exploreTimes;

	private int mapId;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getExploreTimes() {
		return exploreTimes;
	}

	public void setExploreTimes(int exploreTimes) {
		this.exploreTimes = exploreTimes;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
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
