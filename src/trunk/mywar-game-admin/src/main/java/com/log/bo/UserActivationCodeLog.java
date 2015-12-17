package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserActivationCodeLog entity. @author MyEclipse Persistence Tools
 */

public class UserActivationCodeLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private String activationCode;
	private Timestamp createTime;
	private Timestamp effectiveTime;
	private Long userId;
	private String name;
	private Timestamp activationTime;
	private String platform;

	// Constructors

	/** default constructor */
	public UserActivationCodeLog() {
	}

	/** full constructor */
	public UserActivationCodeLog(String activationCode, Long userId, String name,
			Date activationTime) {
		this.activationCode = activationCode;
		this.userId = userId;
		this.name = name;
		this.activationTime = new Timestamp(activationTime.getTime());
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getActivationTime() {
		return this.activationTime;
	}

	public void setActivationTime(Timestamp activationTime) {
		this.activationTime = activationTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setEffectiveTime(Timestamp effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Timestamp getEffectiveTime() {
		return effectiveTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatform() {
		return platform;
	}

}