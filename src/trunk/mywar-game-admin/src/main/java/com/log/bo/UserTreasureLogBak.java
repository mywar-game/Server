package com.log.bo;

import java.sql.Timestamp;

/**
 * UserTreasureLogBak entity. @author MyEclipse Persistence Tools
 */

public class UserTreasureLogBak implements java.io.Serializable {

	// Fields

	private Integer userTreasureLogId;
	private String userId;
	private Integer treasureId;
	private Integer treasureType;
	private Integer category;
	private String operation;
	private Integer changeNum;
	private String extinfo;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserTreasureLogBak() {
	}

	/** minimal constructor */
	public UserTreasureLogBak(String userId, Integer treasureId,
			Integer treasureType, Integer category, String operation,
			Integer changeNum, Timestamp createTime) {
		this.userId = userId;
		this.treasureId = treasureId;
		this.treasureType = treasureType;
		this.category = category;
		this.operation = operation;
		this.changeNum = changeNum;
		this.createTime = createTime;
	}

	/** full constructor */
	public UserTreasureLogBak(String userId, Integer treasureId,
			Integer treasureType, Integer category, String operation,
			Integer changeNum, String extinfo, Timestamp createTime) {
		this.userId = userId;
		this.treasureId = treasureId;
		this.treasureType = treasureType;
		this.category = category;
		this.operation = operation;
		this.changeNum = changeNum;
		this.extinfo = extinfo;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getUserTreasureLogId() {
		return this.userTreasureLogId;
	}

	public void setUserTreasureLogId(Integer userTreasureLogId) {
		this.userTreasureLogId = userTreasureLogId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTreasureId() {
		return this.treasureId;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public Integer getTreasureType() {
		return this.treasureType;
	}

	public void setTreasureType(Integer treasureType) {
		this.treasureType = treasureType;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getChangeNum() {
		return this.changeNum;
	}

	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}

	public String getExtinfo() {
		return this.extinfo;
	}

	public void setExtinfo(String extinfo) {
		this.extinfo = extinfo;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}