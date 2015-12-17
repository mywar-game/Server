package com.fantingame.game.mywar.logic.mail.model;

import java.util.Date;

/**
 * 用户邮件
 * 
 * @author yezp
 */
public class UserMail {

	private int userMailId;

	private String userId;

	/**
	 * 邮件类型 0 后台发送的邮件 1系统内部发放的邮件
	 */
	private int emailType;

	/**
	 * 显示类型 0系统邮件
	 */
	private int emailShowType;

	private String systemMailId;

	private int status;

	private int receiveStatus;
	
	/**
	 * 来自xx用户
	 */
	private String fromUserId;
	private String title;
	private String content;

	/**
	 * 扩展字段
	 */
	private String param;

	/**
	 * 自带附件，如果该字段中有值，则不会去看模板中的附件参数
	 */
	private String rewards;

	private Date createdTime;

	private Date updatedTime;

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

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
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

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
