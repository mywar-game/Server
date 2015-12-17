package com.fantingame.game.gamecenter.bo;

import java.util.Date;

public class UserQueneInfo {

	private String serverId;

	/**
	 * 排队令牌
	 */
	private String queneToken;

	private Date loginTime;

	private long waitingTime;

	private int person;

	private Date removeTime;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getQueneToken() {
		return queneToken;
	}

	public void setQueneToken(String queneToken) {
		this.queneToken = queneToken;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public Date getRemoveTime() {
		return removeTime;
	}

	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}

}
