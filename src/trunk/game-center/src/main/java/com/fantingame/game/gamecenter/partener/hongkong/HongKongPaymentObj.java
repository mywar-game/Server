package com.fantingame.game.gamecenter.partener.hongkong;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class HongKongPaymentObj extends PaymentObj {
	private String json;
	
	private String signature;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
