package com.adminTool.msgbody;

import java.io.Serializable;

/**
 * 用户道具
 * 
 * @author jacky
 * 
 */
public class UserTreasureInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 道具ID
	 */
	private int toolId;

	/**
	 * 道具数量
	 */
	private int toolNum;

	/**
	 * 道具类型
	 */
	private int toolType;

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

	public int getToolType() {
		return toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;
	}

}
