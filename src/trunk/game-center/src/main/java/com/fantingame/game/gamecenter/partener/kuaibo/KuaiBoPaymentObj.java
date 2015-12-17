package com.fantingame.game.gamecenter.partener.kuaibo;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class KuaiBoPaymentObj extends PaymentObj {
	private String merId;
	private String orderId;
	private String money;
	private String tranCode;
	private String encString;
	private String paymentFee;
	private String paymentStatusCode;
	private String note;
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public String getEncString() {
		return encString;
	}
	public void setEncString(String encString) {
		this.encString = encString;
	}
	public String getPaymentFee() {
		return paymentFee;
	}
	public void setPaymentFee(String paymentFee) {
		this.paymentFee = paymentFee;
	}
	public String getPaymentStatusCode() {
		return paymentStatusCode;
	}
	public void setPaymentStatusCode(String paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
