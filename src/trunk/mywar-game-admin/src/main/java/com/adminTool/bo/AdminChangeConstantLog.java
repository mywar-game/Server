package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * AdminChangeConstantLog entity. @author MyEclipse Persistence Tools
 */

public class AdminChangeConstantLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String adminName;
	private Timestamp changeTime;
	private String constantName;
	private Integer changeType;
	private String changeDetail;

	// Constructors

	/** default constructor */
	public AdminChangeConstantLog() {
	}

	/** full constructor */
	public AdminChangeConstantLog(Integer sysNum, String adminName,
			Timestamp changeTime, String constantName, Integer changeType,
			String changeDetail) {
		this.sysNum = sysNum;
		this.adminName = adminName;
		this.changeTime = changeTime;
		this.constantName = constantName;
		this.changeType = changeType;
		this.changeDetail = changeDetail;
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

	public Timestamp getChangeTime() {
		return this.changeTime;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public String getConstantName() {
		return this.constantName;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public Integer getChangeType() {
		return this.changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}

	public String getChangeDetail() {
		return this.changeDetail;
	}

	public void setChangeDetail(String changeDetail) {
		this.changeDetail = changeDetail;
	}

}