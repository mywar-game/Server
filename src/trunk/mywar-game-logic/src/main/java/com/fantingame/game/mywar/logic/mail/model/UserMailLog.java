package com.fantingame.game.mywar.logic.mail.model;

import java.util.Date;

public class UserMailLog {

	private String userId;

	private Date lastReceiveTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastReceiveTime() {
		return lastReceiveTime;
	}

	public void setLastReceiveTime(Date lastReceiveTime) {
		this.lastReceiveTime = lastReceiveTime;
	}

}
