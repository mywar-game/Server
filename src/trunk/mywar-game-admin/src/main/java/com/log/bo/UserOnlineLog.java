package com.log.bo;

import java.sql.Timestamp;

/**
 * UserOnlineLog entity. @author MyEclipse Persistence Tools
 */

public class UserOnlineLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time;
	private Integer onlineAmount;
	private String userStr;

	// Constructors

	/** default constructor */
	public UserOnlineLog() {
	}

	/** minimal constructor */
	public UserOnlineLog(Timestamp time, String userStr) {
		this.time = time;
		this.userStr = userStr;
	}

	/** full constructor */
	public UserOnlineLog(Timestamp time, Integer onlineAmount, String userStr) {
		this.time = time;
		this.onlineAmount = onlineAmount;
		this.userStr = userStr;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getOnlineAmount() {
		return this.onlineAmount;
	}

	public void setOnlineAmount(Integer onlineAmount) {
		this.onlineAmount = onlineAmount;
	}

	public String getUserStr() {
		return this.userStr;
	}

	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}

}