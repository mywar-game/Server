package com.log.bo;

import java.sql.Timestamp;

/**
 * UserResourceLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class UserResourceLog implements java.io.Serializable {

	private Integer userResourceLogId;
	private String userId;
	private String operation;
	private Integer sourceType;
	private Integer changeNum;
	private Integer nowNum;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserResourceLog() {
	}

	/** full constructor */
	public UserResourceLog(String userId, String operation, Integer sourceType,
			Integer changeNum, Integer nowNum, Timestamp createTime) {
		this.userId = userId;
		this.operation = operation;
		this.sourceType = sourceType;
		this.changeNum = changeNum;
		this.nowNum = nowNum;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getUserResourceLogId() {
		return this.userResourceLogId;
	}

	public void setUserResourceLogId(Integer userResourceLogId) {
		this.userResourceLogId = userResourceLogId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getChangeNum() {
		return changeNum;
	}

	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}

	public Integer getNowNum() {
		return nowNum;
	}

	public void setNowNum(Integer nowNum) {
		this.nowNum = nowNum;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}