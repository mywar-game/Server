package com.fantingame.game.gamecenter.partener.yxcq.duoku;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class DuokuPaymentObj extends PaymentObj {
	private String amount;//
	private String orderid;//
	private String aid;//
	private String timetamp;
	private String result;
	private String client_secret;
	private String cardtype;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTimetamp() {
		return timetamp;
	}
	public void setTimetamp(String timetamp) {
		this.timetamp = timetamp;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
}
