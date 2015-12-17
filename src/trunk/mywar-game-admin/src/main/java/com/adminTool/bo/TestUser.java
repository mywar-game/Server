package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * TestUser entity. @author MyEclipse Persistence Tools
 */

public class TestUser implements java.io.Serializable {

	// Fields

	private String userId;
	private Integer sysNum;
	private String userName;
	private String password;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public TestUser() {
	}

	/** full constructor */
	public TestUser(Integer sysNum, String userId, String userName, String password,
			Timestamp createTime) {
		this.sysNum = sysNum;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}