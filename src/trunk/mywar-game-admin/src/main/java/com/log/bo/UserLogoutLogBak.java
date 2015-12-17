package com.log.bo;

import java.sql.Timestamp;

/**
 * UserLogoutLogBak entity. @author MyEclipse Persistence Tools
 */

public class UserLogoutLogBak implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String ip;
	private Timestamp logoutTime;
	private Integer liveMinutes;

	// Constructors

	/** default constructor */
	public UserLogoutLogBak() {
	}

	/** minimal constructor */
	public UserLogoutLogBak(String userId, String ip, Timestamp logoutTime) {
		this.userId = userId;
		this.ip = ip;
		this.logoutTime = logoutTime;
	}

	/** full constructor */
	public UserLogoutLogBak(String userId, String ip, Timestamp logoutTime,
			Integer liveMinutes) {
		this.userId = userId;
		this.ip = ip;
		this.logoutTime = logoutTime;
		this.liveMinutes = liveMinutes;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Integer getLiveMinutes() {
		return this.liveMinutes;
	}

	public void setLiveMinutes(Integer liveMinutes) {
		this.liveMinutes = liveMinutes;
	}

}