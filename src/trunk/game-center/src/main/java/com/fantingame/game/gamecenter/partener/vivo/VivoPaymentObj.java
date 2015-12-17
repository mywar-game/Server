package com.fantingame.game.gamecenter.partener.vivo;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 步步高回调数据
 * 
 * @author yezp
 */
public class VivoPaymentObj extends PaymentObj {

	/**
	 * 验证码
	 */
	private String respCode;
	private String respMsg;
	private String signMethod;
	/**
	 * 签名信息
	 */
	private String signature;
	private String storeId;
	/**
	 * 订单号
	 */
	private String storeOrder;
	/**
	 * 交易流水号
	 */
	private String vivoOrder;
	private String orderAmount;
	private String channel;
	private String channelFee;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreOrder() {
		return storeOrder;
	}

	public void setStoreOrder(String storeOrder) {
		this.storeOrder = storeOrder;
	}

	public String getVivoOrder() {
		return vivoOrder;
	}

	public void setVivoOrder(String vivoOrder) {
		this.vivoOrder = vivoOrder;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(String channelFee) {
		this.channelFee = channelFee;
	}

}
