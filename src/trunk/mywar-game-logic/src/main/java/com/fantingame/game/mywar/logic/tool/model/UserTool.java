package com.fantingame.game.mywar.logic.tool.model;

import java.util.Date;

/**
 * 用户道具
 * 
 * @author Administrator
 *
 */
public class UserTool {

	private String userId;

	private int toolId;

	private int toolNum;

	private int storehouseNum;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getToolNum() {
		return toolNum;
	}

	public void setToolNum(int toolNum) {
		this.toolNum = toolNum;
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
