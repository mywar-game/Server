package com.fantingame.game.gamecenter.partener.pada;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 艺果
 * @author Administrator
 *
 */
public class PadaPaymentObj extends PaymentObj {

	public PadaPaymentObj() {}
	private String appId;
	private String cpOrderId;
	private String cpExInfo;
	private String roleId;
	private String orderId;
	private String orderStatus;
	private String payFee;
	private String productCode;
	private String productName;
	private String productCount;
	private String payTime;
	private String sign;
	
	@Override
	public String toString() {
		return "PadaPaymentObj [appId=" + appId + ", cpOrderId=" + cpOrderId
				+ ", cpExInfo=" + cpExInfo + ", roleId=" + roleId
				+ ", orderId=" + orderId + ", orderStatus=" + orderStatus
				+ ", payFee=" + payFee + ", productCode=" + productCode
				+ ", productName=" + productName + ", productCount="
				+ productCount + ", payTime=" + payTime + ", sign=" + sign
				+ "]";
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCpOrderId() {
		return cpOrderId;
	}
	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}
	public String getCpExInfo() {
		return cpExInfo;
	}
	public void setCpExInfo(String cpExInfo) {
		this.cpExInfo = cpExInfo;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPayFee() {
		return payFee;
	}
	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
