package com.log.bo;

import java.sql.Timestamp;

/**
 * UserMailChangeLog entity. @author MyEclipse Persistence Tools
 */

public class UserMailChangeLog implements java.io.Serializable {

	// Fields

	private Integer userMailChangeLogId;
	private Long userMailId;
	private Integer changeType;
	private Timestamp changeTime;

	// Constructors

	/** default constructor */
	public UserMailChangeLog() {
	}

	/** full constructor */
	public UserMailChangeLog(Long userMailId, Integer changeType,
			Timestamp changeTime) {
		this.userMailId = userMailId;
		this.changeType = changeType;
		this.changeTime = changeTime;
	}

	// Property accessors

	public Integer getUserMailChangeLogId() {
		return this.userMailChangeLogId;
	}

	public void setUserMailChangeLogId(Integer userMailChangeLogId) {
		this.userMailChangeLogId = userMailChangeLogId;
	}

	public Long getUserMailId() {
		return this.userMailId;
	}

	public void setUserMailId(Long userMailId) {
		this.userMailId = userMailId;
	}

	public Integer getChangeType() {
		return this.changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}

	public Timestamp getChangeTime() {
		return this.changeTime;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

}