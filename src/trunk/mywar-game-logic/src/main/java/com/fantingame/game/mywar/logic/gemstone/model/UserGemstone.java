package com.fantingame.game.mywar.logic.gemstone.model;

import java.util.Date;

public class UserGemstone {

	private String userGemstoneId;

	private String userId;

	private String userEquipId;

	private int pos;

	private int storehouseNum;

	private int gemstoneId;

	private String gemstoneAttr;

	private Date createdTime;

	private Date updatedTime;

	public String getUserGemstoneId() {
		return userGemstoneId;
	}

	public void setUserGemstoneId(String userGemstoneId) {
		this.userGemstoneId = userGemstoneId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getGemstoneId() {
		return gemstoneId;
	}

	public void setGemstoneId(int gemstoneId) {
		this.gemstoneId = gemstoneId;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getUserEquipId() {
		return userEquipId;
	}

	public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}

	public String getGemstoneAttr() {
		return gemstoneAttr;
	}

	public void setGemstoneAttr(String gemstoneAttr) {
		this.gemstoneAttr = gemstoneAttr;
	}

	public int getStorehouseNum() {
		return storehouseNum;
	}

	public void setStorehouseNum(int storehouseNum) {
		this.storehouseNum = storehouseNum;
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
