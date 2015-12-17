package com.fantingame.game.gamecenter.partener.wandoujia;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class WDJPaymentObj extends PaymentObj {

	private String timeStamp;

	/**
	 * 豌豆荚订单id
	 */
	private String orderId;

	private String money;

	private String chargeType;

	private String appKeyId;

	private String buyerId;

	/**
	 * 开发者订单号	创建订单时候传入的订单号原样返回
	 */
	private String out_trade_no;

	private String cardNo;

	private String sign;

	private String content;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getAppKeyId() {
		return appKeyId;
	}

	public void setAppKeyId(String appKeyId) {
		this.appKeyId = appKeyId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
