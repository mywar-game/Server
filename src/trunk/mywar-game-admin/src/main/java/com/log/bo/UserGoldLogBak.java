package com.log.bo;

import java.sql.Timestamp;

/**
 * UserGoldLogBak entity. @author MyEclipse Persistence Tools
 */

public class UserGoldLogBak implements java.io.Serializable {

	// Fields

	private Integer logId;
	private String userId;
	private Integer userLevel;
	private Integer category;
	private Integer type;
	private Integer changeNum;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserGoldLogBak() {
	}

	/** full constructor */
	public UserGoldLogBak(String userId, Integer userLevel, Integer category,
			Integer type, Integer changeNum, Timestamp time) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.category = category;
		this.type = type;
		this.changeNum = changeNum;
		this.time = time;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChangeNum() {
		return this.changeNum;
	}

	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}