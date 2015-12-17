package com.fantingame.game.gamecenter.partener.yxcq.appchina;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class AppChinaPaymentObj extends PaymentObj {
	private String exorderno;//外部订单号
	private String transid;//交易流水号
	private String appid;//应用代码
	private String waresid;//商品编码
	private String feetype;//计费方式
	private String money;//交易金额
	private String count;//购买数量
	private String result;//交易结果
	private String transtype;//交易类型
	private String transtime;//交易时间
	private String cpprivate;//商户私有信息
	private String transdata;
	private String paytype;
	private String sign;
	public String getExorderno() {
		return exorderno;
	}
	public void setExorderno(String exorderno) {
		this.exorderno = exorderno;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getWaresid() {
		return waresid;
	}
	public void setWaresid(String waresid) {
		this.waresid = waresid;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}
	public String getTranstime() {
		return transtime;
	}
	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}
	public String getCpprivate() {
		return cpprivate;
	}
	public void setCpprivate(String cpprivate) {
		this.cpprivate = cpprivate;
	}
	public String getTransdata() {
		return transdata;
	}
	public void setTransdata(String transdata) {
		this.transdata = transdata;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
}
