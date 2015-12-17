package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserGoldLog entity. @author MyEclipse Persistence Tools
 */

public class UserGoldLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private String userId;
	private String userName;
	private int lodoId;
	private Integer userLevel;
	private Integer category;
	private Integer type;
	private Integer changeNum;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserGoldLog() {
	}

	/** full constructor */
	public UserGoldLog(String userId, Integer userLevel, Integer category,
			Integer type, Integer changeNum, Timestamp time) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.category = category;
		this.type = type;
		this.changeNum = changeNum;
		this.time = time;
	}
	
	public UserGoldLog(String userId, String userName, int lodoId, Integer userLevel, Integer category,
			Integer type, Integer changeNum, Date time) {
		this.userId = userId;
		this.userName = userName;
		this.lodoId = lodoId;
		this.userLevel = userLevel;
		this.category = category;
		this.type = type;
		this.changeNum = changeNum;
		this.time = new Timestamp(time.getTime());
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

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}


}