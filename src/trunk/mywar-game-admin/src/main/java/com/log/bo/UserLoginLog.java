package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserLoginLog entity. @author MyEclipse Persistence Tools
 */

public class UserLoginLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer level;
	private String ip;
	private Timestamp loginTime;
	
	/**
	 * 角色名
	 */
	private String userName;
	private int lodoId;

	// Constructors

	/** default constructor */
	public UserLoginLog() {
	}

	/** full constructor */
	public UserLoginLog(String userId, Integer level, String ip, Date loginTime, String userName,int lodoId) {
		this.userId = userId;
		this.level = level;
		this.ip = ip;
		this.loginTime = new Timestamp(loginTime.getTime());
		this.userName = userName;
		this.lodoId = lodoId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}


}