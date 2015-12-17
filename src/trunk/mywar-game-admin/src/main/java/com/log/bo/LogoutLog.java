package com.log.bo;

public class LogoutLog {

	public LogoutLog() {}
	
	private String userId;
	private Integer onlineTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}
}
