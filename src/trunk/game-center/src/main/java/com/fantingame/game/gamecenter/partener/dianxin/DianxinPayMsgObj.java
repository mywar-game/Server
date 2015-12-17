package com.fantingame.game.gamecenter.partener.dianxin;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class DianxinPayMsgObj extends PaymentObj {

	public DianxinPayMsgObj() {}
	
	private String cp_order_id;
	private String correlator;
	private String result_code;
	private String result_desc;
	private int fee;
	private String pay_type;
	private String sign;
	
	public String getCp_order_id() {
		return cp_order_id;
	}
	public void setCp_order_id(String cp_order_id) {
		this.cp_order_id = cp_order_id;
	}
	public String getCorrelator() {
		return correlator;
	}
	public void setCorrelator(String correlator) {
		this.correlator = correlator;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_desc() {
		return result_desc;
	}
	public void setResult_desc(String result_desc) {
		this.result_desc = result_desc;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
