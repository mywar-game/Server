package com.adminTool.msgbody;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户邮件
 * @author Administrator
 *
 */
public class ResUserMail implements Serializable{

	private static final long serialVersionUID = -474268007056026923L;

	private int userMailId;

	private String userId;
	//邮件逻辑类型 0后台发放的邮件 大于0 系统内部邮件
	private int emailType;
	//显示类型  0系统邮件
	private int emailShowType;
	
	private String systemMailId;

	private int status;

	private int receiveStatus;
	
	private String param;

	private Date createdTime;

	private Date updatedTime;
	
	private Integer sourceId; // 

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public int getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(int userMailId) {
		this.userMailId = userMailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSystemMailId() {
		return systemMailId;
	}

	public void setSystemMailId(String systemMailId) {
		this.systemMailId = systemMailId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(int receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}

	public int getEmailType() {
		return emailType;
	}

	public void setEmailType(int emailType) {
		this.emailType = emailType;
	}

	public int getEmailShowType() {
		return emailShowType;
	}

	public void setEmailShowType(int emailShowType) {
		this.emailShowType = emailShowType;
	}
}
