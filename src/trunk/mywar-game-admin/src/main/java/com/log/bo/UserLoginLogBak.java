package com.log.bo;

import java.sql.Timestamp;

/**
 * UserLoginLogBak entity. @author MyEclipse Persistence Tools
 */

public class UserLoginLogBak implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer level;
	private String ip;
	private Timestamp loginTime;

	// Constructors

	/** default constructor */
	public UserLoginLogBak() {
	}

	/** full constructor */
	public UserLoginLogBak(String userId, Integer level, String ip,
			Timestamp loginTime) {
		this.userId = userId;
		this.level = level;
		this.ip = ip;
		this.loginTime = loginTime;
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

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

}