package com.fantingame.game.mywar.logic.user.bean;

/**
 * 存储一些在线用户的部分信息，不需要每次都取数据库
 * 
 * @author yezp
 */
public class UserObject {

	private int camp;

	private String partnerId;

	public int getCamp() {
		return camp;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

}
