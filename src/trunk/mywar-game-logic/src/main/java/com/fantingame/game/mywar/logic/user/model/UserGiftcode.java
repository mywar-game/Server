package com.fantingame.game.mywar.logic.user.model;

import java.util.Date;

/**
 * 用户礼包码
 * 
 * @author yezp
 */
public class UserGiftcode {

	private String userId;

	private int giftbagId;

	private int type;

	private int totalNum;

	private Date createdTime;

	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
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

	public int getGiftbagId() {
		return giftbagId;
	}

	public void setGiftbagId(int giftbagId) {
		this.giftbagId = giftbagId;
	}

}
