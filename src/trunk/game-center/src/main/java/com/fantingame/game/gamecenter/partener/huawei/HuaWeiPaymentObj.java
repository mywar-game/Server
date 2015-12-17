package com.fantingame.game.gamecenter.partener.huawei;

import java.util.Map;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 华为
 * 
 * @author yezp
 */
public class HuaWeiPaymentObj extends PaymentObj {

	/**
	 * "0" 表示成功， "1" 表示退款成功（暂未启用）
	 */
	private String result;
	private String userName;
	private String productName;
	private String payType;
	private String amount;
	private String orderId;
	private String notifyTime;
	private String requestId;
	private String bankId;
	private String orderTime;
	private String tradeTime;
	private String accessMode;
	private String spending;
	private String extReserved;
	private String sign;

	private Map<String, Object> map;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}

	public String getSpending() {
		return spending;
	}

	public void setSpending(String spending) {
		this.spending = spending;
	}

	public String getExtReserved() {
		return extReserved;
	}

	public void setExtReserved(String extReserved) {
		this.extReserved = extReserved;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
