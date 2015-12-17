package com.fantingame.game.gamecenter.partener.kaiying;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class KaiYingTwPaymentObj extends PaymentObj {
	private String ts;
	private String sig;
	private String kda;
	private String userId;
	private String sid;
	private String number;
	private String amount;
	private String roleId;
	private String partnerOrderId;
	private String active1;
	private String active2;
	private String payRef;
	private String appExtra1;
	private String appExtra2;
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	public String getKda() {
		return kda;
	}
	public void setKda(String kda) {
		this.kda = kda;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getPartnerOrderId() {
		return partnerOrderId;
	}
	public void setPartnerOrderId(String partnerOrderId) {
		this.partnerOrderId = partnerOrderId;
	}
	public String getActive1() {
		return active1;
	}
	public void setActive1(String active1) {
		this.active1 = active1;
	}
	public String getActive2() {
		return active2;
	}
	public void setActive2(String active2) {
		this.active2 = active2;
	}
	public String getPayRef() {
		return payRef;
	}
	public void setPayRef(String payRef) {
		this.payRef = payRef;
	}
	public String getAppExtra1() {
		return appExtra1;
	}
	public void setAppExtra1(String appExtra1) {
		this.appExtra1 = appExtra1;
	}
	public String getAppExtra2() {
		return appExtra2;
	}
	public void setAppExtra2(String appExtra2) {
		this.appExtra2 = appExtra2;
	}
	
}
