package com.fantingame.game.mywar.logic.mall.model;

import java.util.Date;

public class UserBuyBackInfo {

	private String buyBackId;

	private String userId;

	private String userEquipId;

	private int toolId;

	private int toolType;

	private int toolNum;

	private String equipMainAttr;

	private String equipSecondaryAttr;

	private Date createdTime;

	private Date updatedTime;

	public String getBuyBackId() {
		return buyBackId;
	}

	public void setBuyBackId(String buyBackId) {
		this.buyBackId = buyBackId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEquipId() {
		return userEquipId;
	}

	public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getToolType() {
		return toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;
	}

	public int getToolNum() {
		return toolNum;
	}

	public void setToolNum(int toolNum) {
		this.toolNum = toolNum;
	}

	public String getEquipMainAttr() {
		return equipMainAttr;
	}

	public void setEquipMainAttr(String equipMainAttr) {
		this.equipMainAttr = equipMainAttr;
	}

	public String getEquipSecondaryAttr() {
		return equipSecondaryAttr;
	}

	public void setEquipSecondaryAttr(String equipSecondaryAttr) {
		this.equipSecondaryAttr = equipSecondaryAttr;
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
