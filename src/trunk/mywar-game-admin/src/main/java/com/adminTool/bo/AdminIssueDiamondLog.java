package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * AdminIssueDiamondLog entity. @author MyEclipse Persistence Tools
 */

public class AdminIssueDiamondLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String adminName;
	private Timestamp issueTime;
	private String receiveUser;
	private String failUser;
	private Integer num;
	private String issueReason;

	// Constructors

	/** default constructor */
	public AdminIssueDiamondLog() {
	}

	/** full constructor */
	public AdminIssueDiamondLog(Integer sysNum, String adminName,
			Timestamp issueTime, String receiveUser, String failUser,Integer num,
			String issueReason) {
		this.sysNum = sysNum;
		this.adminName = adminName;
		this.issueTime = issueTime;
		this.receiveUser = receiveUser;
		this.failUser = failUser;
		this.num = num;
		this.issueReason = issueReason;
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

	public Timestamp getIssueTime() {
		return this.issueTime;
	}

	public void setIssueTime(Timestamp issueTime) {
		this.issueTime = issueTime;
	}

	public String getReceiveUser() {
		return this.receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getFailUser() {
		return failUser;
	}

	public void setFailUser(String failUser) {
		this.failUser = failUser;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getIssueReason() {
		return this.issueReason;
	}

	public void setIssueReason(String issueReason) {
		this.issueReason = issueReason;
	}

}