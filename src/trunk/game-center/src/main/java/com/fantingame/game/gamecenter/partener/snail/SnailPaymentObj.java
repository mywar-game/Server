package com.fantingame.game.gamecenter.partener.snail;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 蜗牛
 * 
 * @author yezp
 */
public class SnailPaymentObj extends PaymentObj {

	/**
	 * 应用ID
	 */
	private String appId;
	private String act;
	private String productName;
	private String consumeStreamId;
	/**
	 * 订单号
	 */
	private String cooOrderSerial;
	private String uin;
	private String goodsId;
	private String goodsInfo;
	private String goodsCount;
	private String originalMoney;
	/**
	 * 实际总价
	 */
	private String orderMoney;
	private String note;
	/**
	 * 支付状态：0=失败，1=成功
	 */
	private String payStatus;
	private String createTime;
	private String sign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
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

}
