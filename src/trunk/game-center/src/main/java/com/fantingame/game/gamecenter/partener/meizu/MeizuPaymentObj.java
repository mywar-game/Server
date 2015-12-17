package com.fantingame.game.gamecenter.partener.meizu;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class MeizuPaymentObj extends PaymentObj {

	public MeizuPaymentObj () {}
	private String username;
	private String change_id;
	private String money;
	private String hash;
	private String object;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getChange_id() {
		return change_id;
	}
	public void setChange_id(String change_id) {
		this.change_id = change_id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
}
