package com.log.bo;

import java.sql.Timestamp;

/**
 * UserRegLinkLog entity. @author MyEclipse Persistence Tools
 */

public class UserRegLinkLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private String regLinkId;
	private Long userId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserRegLinkLog() {
	}

	/** full constructor */
	public UserRegLinkLog(String regLinkId, Long userId, Timestamp createTime) {
		this.regLinkId = regLinkId;
		this.userId = userId;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getRegLinkId() {
		return this.regLinkId;
	}

	public void setRegLinkId(String regLinkId) {
		this.regLinkId = regLinkId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}