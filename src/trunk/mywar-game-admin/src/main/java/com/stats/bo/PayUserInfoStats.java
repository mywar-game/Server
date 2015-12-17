package com.stats.bo;

import java.sql.Timestamp;

/**
 * PayUserInfoStats entity. @author MyEclipse Persistence Tools
 */

public class PayUserInfoStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String name;
	private String userName;
	private Timestamp regTime;
	private Timestamp lastLoginTime;
	private Integer logCondition;
	private Integer totalOnlineTime;
	private Integer averageOnlineTime;
	private Integer averagePayPeriod;
	private String guildName;

	// Constructors

	/** default constructor */
	public PayUserInfoStats() {
	}

	/** full constructor */
	public PayUserInfoStats(Integer sysNum, String name, String userName,
			Timestamp regTime, Timestamp lastLoginTime, Integer logCondition,
			Integer totalOnlineTime, Integer averageOnlineTime,
			Integer averagePayPeriod, String guildName) {
		this.sysNum = sysNum;
		this.name = name;
		this.userName = userName;
		this.regTime = regTime;
		this.lastLoginTime = lastLoginTime;
		this.logCondition = logCondition;
		this.totalOnlineTime = totalOnlineTime;
		this.averageOnlineTime = averageOnlineTime;
		this.averagePayPeriod = averagePayPeriod;
		this.guildName = guildName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLogCondition() {
		return this.logCondition;
	}

	public void setLogCondition(Integer logCondition) {
		this.logCondition = logCondition;
	}

	public Integer getTotalOnlineTime() {
		return this.totalOnlineTime;
	}

	public void setTotalOnlineTime(Integer totalOnlineTime) {
		this.totalOnlineTime = totalOnlineTime;
	}

	public Integer getAverageOnlineTime() {
		return this.averageOnlineTime;
	}

	public void setAverageOnlineTime(Integer averageOnlineTime) {
		this.averageOnlineTime = averageOnlineTime;
	}

	public Integer getAveragePayPeriod() {
		return this.averagePayPeriod;
	}

	public void setAveragePayPeriod(Integer averagePayPeriod) {
		this.averagePayPeriod = averagePayPeriod;
	}

	public String getGuildName() {
		return this.guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

}