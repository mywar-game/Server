package com.dataconfig.bo;

/**
 * ScScreenConstant entity. @author MyEclipse Persistence Tools
 */

public class ScScreenConstant implements java.io.Serializable {

	// Fields

	private Integer screenId;
	private String screenName;
	private Integer type;

	// Constructors

	/** default constructor */
	public ScScreenConstant() {
	}

	/** full constructor */
	public ScScreenConstant(String screenName, Integer type) {
		this.screenName = screenName;
		this.type = type;
	}

	// Property accessors

	public Integer getScreenId() {
		return this.screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}