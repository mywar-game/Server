package com.log.bo;

import java.sql.Timestamp;

/**
 * GuildLog entity. @author MyEclipse Persistence Tools
 */

public class GuildLog implements java.io.Serializable {

	// Fields

	private Integer guildLogId;
	private Long guildId;
	private String guildName;
	private Long userId;
	private Long optUserId;
	private String operation;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public GuildLog() {
	}

	/** full constructor */
	public GuildLog(Long guildId, String guildName, Long userId,
			Long optUserId, String operation, Timestamp createTime) {
		this.guildId = guildId;
		this.guildName = guildName;
		this.userId = userId;
		this.optUserId = optUserId;
		this.operation = operation;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getGuildLogId() {
		return this.guildLogId;
	}

	public void setGuildLogId(Integer guildLogId) {
		this.guildLogId = guildLogId;
	}

	public Long getGuildId() {
		return this.guildId;
	}

	public void setGuildId(Long guildId) {
		this.guildId = guildId;
	}

	public String getGuildName() {
		return this.guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOptUserId() {
		return this.optUserId;
	}

	public void setOptUserId(Long optUserId) {
		this.optUserId = optUserId;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}