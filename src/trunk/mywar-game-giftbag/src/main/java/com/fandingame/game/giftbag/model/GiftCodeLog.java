package com.fandingame.game.giftbag.model;

import java.util.Date;

public class GiftCodeLog {
	private String code;
	private int giftBagId;
	private String userId;
	private String serverId;
	private String partenerId;
	private String qn;
	private Date createdTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getPartenerId() {
		return partenerId;
	}

	public void setPartenerId(String partenerId) {
		this.partenerId = partenerId;
	}

	public int getGiftBagId() {
		return giftBagId;
	}

	public void setGiftBagId(int giftBagId) {
		this.giftBagId = giftBagId;
	}

}