package com.log.bo;

import java.sql.Timestamp;

/**
 * UserActionLog entity. @author MyEclipse Persistence Tools
 */

public class UserActionLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer userLevel;
	private String ip;
	private Integer source;
	private Integer actionId;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserActionLog() {
	}

	/** minimal constructor */
	public UserActionLog(String ip, Integer actionId, Timestamp time) {
		this.ip = ip;
		this.actionId = actionId;
		this.time = time;
	}

	/** full constructor */
	public UserActionLog(String userId, Integer userLevel, String ip,
			Integer source, Integer actionId, Timestamp time) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.ip = ip;
		this.source = source;
		this.actionId = actionId;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}