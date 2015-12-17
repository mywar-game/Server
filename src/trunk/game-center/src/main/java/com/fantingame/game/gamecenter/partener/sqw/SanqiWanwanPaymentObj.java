package com.fantingame.game.gamecenter.partener.sqw;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class SanqiWanwanPaymentObj extends PaymentObj {
	private String usergameid;
	private String orderid;
	private String status;
	private String totalPrice;
	private String txNo;
	private String payTime;
	private String payType;
	private String sign;
	private String date;
	public String getUsergameid() {
		return usergameid;
	}
	public void setUsergameid(String usergameid) {
		this.usergameid = usergameid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTxNo() {
		return txNo;
	}
	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
