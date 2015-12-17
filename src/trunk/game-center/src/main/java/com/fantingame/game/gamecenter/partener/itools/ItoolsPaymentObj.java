package com.fantingame.game.gamecenter.partener.itools;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class ItoolsPaymentObj extends PaymentObj {
	private String order_id_com;//发起支付时的订单号
	private String user_id;//支付的用户id
	private String amount;//成功支付的金額
	private String account;//支付账号
	private String order_id;//支付平台的订单号
	private String result;//支付结果,目前只有'success'成功
	private String notify_data;
	private String sign;
	public String getOrder_id_com() {
		return order_id_com;
	}
	public void setOrder_id_com(String order_id_com) {
		this.order_id_com = order_id_com;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getNotify_data() {
		return notify_data;
	}
	public void setNotify_data(String notify_data) {
		this.notify_data = notify_data;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
