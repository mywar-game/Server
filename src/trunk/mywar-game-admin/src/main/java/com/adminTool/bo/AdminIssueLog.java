package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * AdminIssueLog entity. @author MyEclipse Persistence Tools
 */

public class AdminIssueLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String adminName;
	private Timestamp issueTime;
	private String receiveUser;
	private String failUser;
	private Integer receiveUserType;
	private String mailAttach;
	private String theme;
	private String content;
	private String issueReason;

	// Constructors

	/** default constructor */
	public AdminIssueLog() {
	}

	/** full constructor */
	public AdminIssueLog(Integer sysNum, String adminName, Timestamp issueTime,
			String receiveUser,String failUser, Integer receiveUserType, String mailAttach,
			String theme, String content, String issueReason) {
		this.sysNum = sysNum;
		this.adminName = adminName;
		this.issueTime = issueTime;
		this.receiveUser = receiveUser;
		this.failUser = failUser;
		this.receiveUserType = receiveUserType;
		this.mailAttach = mailAttach;
		this.theme = theme;
		this.content = content;
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

	public Integer getReceiveUserType() {
		return this.receiveUserType;
	}

	public void setReceiveUserType(Integer receiveUserType) {
		this.receiveUserType = receiveUserType;
	}

	public String getMailAttach() {
		return this.mailAttach;
	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFailUser() {
		return failUser;
	}

	public void setFailUser(String failUser) {
		this.failUser = failUser;
	}

	public String getIssueReason() {
		return this.issueReason;
	}

	public void setIssueReason(String issueReason) {
		this.issueReason = issueReason;
	}

}