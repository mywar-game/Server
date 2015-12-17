package com.fantingame.game.gamecenter.partener.anzhi;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class AnZhiPaymentObj extends PaymentObj {
	private String uid;//安智账号id
	private String orderId;//订单号
	private String orderAmount;//订单金额(单位为分)
	private String orderTime;//支付时间
	private String orderAccount;//支付账号
	private String code;//订单状态 （1为成功）
	private String payAmount;//实际支付金额(单位为分)
	private String AppInfo;//回调信息
	private String notifyTime;//通知时间
	private String memo;//备注
	private String data;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderAccount() {
		return orderAccount;
	}
	public void setOrderAccount(String orderAccount) {
		this.orderAccount = orderAccount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getAppInfo() {
		return AppInfo;
	}
	public void setAppInfo(String appInfo) {
		AppInfo = appInfo;
	}
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
