package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * AdminDealSuggestLog entity. @author MyEclipse Persistence Tools
 */

public class AdminDealSuggestLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String adminName;
	private Integer suggestId;
	private Integer suggestType;
	private Integer dealType;
	private Timestamp dealTime;

	// Constructors

	/** default constructor */
	public AdminDealSuggestLog() {
	}

	/** full constructor */
	public AdminDealSuggestLog(Integer sysNum, String adminName,
			Integer suggestId, Integer suggestType, Integer dealType,
			Timestamp dealTime) {
		this.sysNum = sysNum;
		this.adminName = adminName;
		this.suggestId = suggestId;
		this.suggestType = suggestType;
		this.dealType = dealType;
		this.dealTime = dealTime;
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

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getSuggestId() {
		return this.suggestId;
	}

	public void setSuggestId(Integer suggestId) {
		this.suggestId = suggestId;
	}

	public Integer getSuggestType() {
		return this.suggestType;
	}

	public void setSuggestType(Integer suggestType) {
		this.suggestType = suggestType;
	}

	public Integer getDealType() {
		return this.dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Timestamp getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

}