package com.fantingame.game.gamecenter.partener.yxcq.chinamobile;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class ChinaMobilePaymentObj extends PaymentObj {
	private String feeType;
	private String versionId;
	private String cpid;
	private String contentId;
	private String channelId;
	private String consumerCode;
	private String password;
	private String cpparam;
	private String packageID;
	private String hRet;
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getConsumerCode() {
		return consumerCode;
	}
	public void setConsumerCode(String consumerCode) {
		this.consumerCode = consumerCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpparam() {
		return "01"+YXCQChinaMobileSdk.instance().getPartnerId()+cpparam;
	}
	public void setCpparam(String cpparam) {
		this.cpparam = cpparam;
	}
	public String getPackageID() {
		return packageID;
	}
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
	public String gethRet() {
		return hRet;
	}
	public void sethRet(String hRet) {
		this.hRet = hRet;
	}
}
