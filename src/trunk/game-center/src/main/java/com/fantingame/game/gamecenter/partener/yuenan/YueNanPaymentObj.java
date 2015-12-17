package com.fantingame.game.gamecenter.partener.yuenan;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class YueNanPaymentObj extends PaymentObj {
	private String status;
	private String sandbox;
	private String transactionType;
	private String transactionId;
	private String amount;
	private String currency;
	private String state;
	private String target;
	private String countryCode;
	private String hash;
	private String phone;
	private String message;
	private String code;
	private String cardCode;
	private String cardSerial;
	private String cardVendor;
	private String productId;
	private boolean isGoogle;
	private boolean isApple;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSandbox() {
		return sandbox;
	}
	public void setSandbox(String sandbox) {
		this.sandbox = sandbox;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardSerial() {
		return cardSerial;
	}
	public void setCardSerial(String cardSerial) {
		this.cardSerial = cardSerial;
	}
	public String getCardVendor() {
		return cardVendor;
	}
	public void setCardVendor(String cardVendor) {
		this.cardVendor = cardVendor;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public boolean isGoogle() {
		return isGoogle;
	}
	public void setGoogle(boolean isGoogle) {
		this.isGoogle = isGoogle;
	}
	public boolean isApple() {
		return isApple;
	}
	public void setApple(boolean isApple) {
		this.isApple = isApple;
	}
}
