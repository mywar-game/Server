package com.fantingame.game.gamecenter.partener.yxcq.apple;

import java.util.Map;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class ApplePaymentObj extends PaymentObj {
	private String appleOrderId;
	private String receipt;
	private String gameOrderId;
	private String money;
	private Map<String, Object> result;
	private AppleOrder appleOrder;
	private String version;

	public String getAppleOrderId() {
		return appleOrderId;
	}

	public void setAppleOrderId(String appleOrderId) {
		this.appleOrderId = appleOrderId;
	}

	public String getGameOrderId() {
		return gameOrderId;
	}

	public void setGameOrderId(String gameOrderId) {
		this.gameOrderId = gameOrderId;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public AppleOrder getAppleOrder() {
		return appleOrder;
	}

	public void setAppleOrder(AppleOrder appleOrder) {
		this.appleOrder = appleOrder;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
