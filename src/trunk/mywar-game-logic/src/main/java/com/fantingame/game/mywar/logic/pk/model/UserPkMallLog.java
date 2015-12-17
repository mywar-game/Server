package com.fantingame.game.mywar.logic.pk.model;

import java.util.Date;

public class UserPkMallLog {

	private String userId;

	private int mallId;

	private int dayBuyNum;

	private int totalBuyNum;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public int getDayBuyNum() {
		return dayBuyNum;
	}

	public void setDayBuyNum(int dayBuyNum) {
		this.dayBuyNum = dayBuyNum;
	}

	public int getTotalBuyNum() {
		return totalBuyNum;
	}

	public void setTotalBuyNum(int totalBuyNum) {
		this.totalBuyNum = totalBuyNum;
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
