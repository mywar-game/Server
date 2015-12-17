package com.fantingame.game.gamecenter.partener.tongbu;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class TongBuPaymentObj extends PaymentObj{
	
	private String source;
	
	private String tradeNo;
	
	private String amount;
	
	private String partner;
	
	private String paydes;
	
	private String sign;
	
	private String debug;
	
	private String tborder;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPaydes() {
		return paydes;
	}

	public void setPaydes(String paydes) {
		this.paydes = paydes;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getTborder() {
		return tborder;
	}

	public void setTborder(String tborder) {
		this.tborder = tborder;
	}
	
}
