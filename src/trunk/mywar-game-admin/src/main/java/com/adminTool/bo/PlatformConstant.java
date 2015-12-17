package com.adminTool.bo;

/**
 * PlatformConstant entity. @author MyEclipse Persistence Tools
 */

public class PlatformConstant implements java.io.Serializable {

	// Fields

	private Integer id;
	private String platform;

	// Constructors

	/** default constructor */
	public PlatformConstant() {
	}

	/** full constructor */
	public PlatformConstant(String platform) {
		this.platform = platform;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}