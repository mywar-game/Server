package com.log.bo;

import java.sql.Timestamp;

/**
 * UserVipLog entity. @author MyEclipse Persistence Tools
 */

public class UserVipLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer vipLevel;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserVipLog() {
	}

	/** full constructor */
	public UserVipLog(String userId, Integer vipLevel, Timestamp time) {
		this.userId = userId;
		this.vipLevel = vipLevel;
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

	public Integer getVipLevel() {
		return this.vipLevel;
	}

	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}