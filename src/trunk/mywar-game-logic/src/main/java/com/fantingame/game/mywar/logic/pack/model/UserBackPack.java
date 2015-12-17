package com.fantingame.game.mywar.logic.pack.model;

import java.util.Date;

public class UserBackPack {
    private int userBackPackId;
	private String userId;
	private int goodsType;
	private String userGoodsId;
	private int pos;
	private Date createdTime;
	private Date updatedTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	public String getUserGoodsId() {
		return userGoodsId;
	}
	public void setUserGoodsId(String userGoodsId) {
		this.userGoodsId = userGoodsId;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
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
	public int getUserBackPackId() {
		return userBackPackId;
	}
	public void setUserBackPackId(int userBackPackId) {
		this.userBackPackId = userBackPackId;
	}
}
