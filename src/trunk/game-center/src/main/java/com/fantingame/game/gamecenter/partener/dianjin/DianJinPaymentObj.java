package com.fantingame.game.gamecenter.partener.dianjin;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class DianJinPaymentObj extends PaymentObj{
	
	private String appId;//应用id
	private String act;  //等于1
	private String productName;//产品名称
	private String consumeStreamId;//消费流水号91的订单号
	private String cooOrderSerial;//商户订单号
	private String uin;//91账号id
	private String goodsId;//商品 ID
	private String goodsInfo;//商品名称
	private String goodsCount;//商品数量
	private String originalMoney;//原始总价
	private String orderMoney;//实际总价
	private String note;//支付描述
	private String payStatus;//支付状态 支付状态：0=失败，1=成功
	private String createTime;//创建时间(yyyy-MM-dd HH:mm:ss)
	private String sign;//签名

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getConsumeStreamId() {
		return consumeStreamId;
	}

	public void setConsumeStreamId(String consumeStreamId) {
		this.consumeStreamId = consumeStreamId;
	}

	public String getCooOrderSerial() {
		return cooOrderSerial;
	}

	public void setCooOrderSerial(String cooOrderSerial) {
		this.cooOrderSerial = cooOrderSerial;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getOriginalMoney() {
		return originalMoney;
	}

	public void setOriginalMoney(String originalMoney) {
		this.originalMoney = originalMoney;
	}

	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
