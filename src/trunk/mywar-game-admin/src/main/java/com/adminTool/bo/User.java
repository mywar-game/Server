package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String name;
	private Integer sex;
	private Integer lodoId;
	private Timestamp regTime;
	private Integer mainRoleId;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String name, Integer sex, Timestamp regTime, Integer mainRoleId) {
		this.userName = userName;
		this.name = name;
		this.sex = sex;
		this.regTime = regTime;
		this.mainRoleId = mainRoleId;
	}

	/** full constructor */
	public User(String userName, String name, Integer sex, Integer lodoId,
			Timestamp regTime, Integer mainRoleId) {
		this.userName = userName;
		this.name = name;
		this.sex = sex;
		this.lodoId = lodoId;
		this.regTime = regTime;
		this.mainRoleId = mainRoleId;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getLodoId() {
		return this.lodoId;
	}

	public void setLodoId(Integer lodoId) {
		this.lodoId = lodoId;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Integer getMainRoleId() {
		return mainRoleId;
	}

	public void setMainRoleId(Integer mainRoleId) {
		this.mainRoleId = mainRoleId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", name="
				+ name + ", sex=" + sex + ", lodoId=" + lodoId + ", regTime="
				+ regTime + ", mainRoleId=" + mainRoleId + "]";
	}

}