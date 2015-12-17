package com.adminTool.bo;

/**
 * UserType entity. @author MyEclipse Persistence Tools
 */

public class UserType implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String userId;
	private Integer type;

	// Constructors

	/** default constructor */
	public UserType() {
	}

	/** full constructor */
	public UserType(Integer sysNum, String userId, Integer type) {
		this.sysNum = sysNum;
		this.userId = userId;
		this.type = type;
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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}