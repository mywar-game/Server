package com.stats.bo;

import java.util.Date;

/**
 * UserLevelIntervalConsumeTypeStats entity. @author MyEclipse Persistence Tools
 */

public class UserLevelIntervalConsumeTypeStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private String consumeInfo;
	private String testUserConsumeInfo;

	// Constructors

	/** default constructor */
	public UserLevelIntervalConsumeTypeStats() {
	}

	/** full constructor */
	public UserLevelIntervalConsumeTypeStats(Integer sysNum, Date date,
			String consumeInfo, String testUserConsumeInfo) {
		this.sysNum = sysNum;
		this.date = date;
		this.consumeInfo = consumeInfo;
		this.testUserConsumeInfo = testUserConsumeInfo;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getConsumeInfo() {
		return this.consumeInfo;
	}

	public void setConsumeInfo(String consumeInfo) {
		this.consumeInfo = consumeInfo;
	}

	public String getTestUserConsumeInfo() {
		return this.testUserConsumeInfo;
	}

	public void setTestUserConsumeInfo(String testUserConsumeInfo) {
		this.testUserConsumeInfo = testUserConsumeInfo;
	}

}