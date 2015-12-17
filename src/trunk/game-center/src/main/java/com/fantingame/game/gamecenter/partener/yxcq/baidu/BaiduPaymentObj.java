package com.fantingame.game.gamecenter.partener.yxcq.baidu;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class BaiduPaymentObj extends PaymentObj {
	private String apiKey;
	private String userId;
	private String serverId;
	private String timestamp;
	private String orderId;
	private String wanbaOid;
	private String amount;
	private String currency;
	private String result;
	private String backSend;
	private String customInfo;
	private String sign;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getWanbaOid() {
		return wanbaOid;
	}

	public void setWanbaOid(String wanbaOid) {
		this.wanbaOid = wanbaOid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBackSend() {
		return backSend;
	}

	public void setBackSend(String backSend) {
		this.backSend = backSend;
	}

	public String getCustomInfo() {
		return customInfo;
	}

	public void setCustomInfo(String customInfo) {
		this.customInfo = customInfo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
