package com.fantingame.game.mywar.logic.user.model;

import java.io.Serializable;
import java.util.Date;

public class UserOnlineLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private int logId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 登录时间
	 */
	private Date loginTime;

	/**
	 * 登出时间
	 */
	private Date logoutTime;

	/**
	 * 用户IP
	 */
	private String userIp;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

}
