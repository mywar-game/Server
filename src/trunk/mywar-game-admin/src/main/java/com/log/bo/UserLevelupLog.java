package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserLevelupLog entity. @author MyEclipse Persistence Tools
 */

public class UserLevelupLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time;
	private String userId;
	private Integer level;
	private String userName;
	private int lodoId;

	// Constructors

	/** default constructor */
	public UserLevelupLog() {
	}

	/** full constructor */
	public UserLevelupLog(Date time, String userId, Integer level, String userName, int lodoId) {
		this.time = new Timestamp(time.getTime());
		this.userId = userId;
		this.level = level;
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

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
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