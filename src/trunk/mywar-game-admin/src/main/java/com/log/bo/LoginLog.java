package com.log.bo;

import java.util.Date;

public class LoginLog {

	public LoginLog() {}
	private String userId;
	private Date lastLoginTime;
	private Integer loginDays;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getLoginDays() {
		return loginDays;
	}
	public void setLoginDays(Integer loginDays) {
		this.loginDays = loginDays;
	}
}
