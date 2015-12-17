package com.fantingame.game.gamecenter.partener.lenovo;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class TransData extends PaymentObj {
	private String sign;
	private String transData;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}
}
