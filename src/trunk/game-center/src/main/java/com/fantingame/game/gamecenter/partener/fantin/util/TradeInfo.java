package com.fantingame.game.gamecenter.partener.fantin.util;

import java.io.Serializable;


/**
 * 订单信息，应存储在接入方的服务器中，有服务器生成订单信息后传递给客户端，再由客户端传递给宜搜SDK
 * 
 * @author ted
 * 
 */
public final class TradeInfo implements Serializable {

	/**
	 * 通知回调 url
	 */
	private static final long serialVersionUID = 201305131626L;
	private String tradeId = ""; // 订单ID
	private String tradeName = ""; // 订单名称
	private String tradeDesc = ""; // 订单描述
	private String reqFee = ""; // 订单金额
	private boolean separable = false; // 是否进行订单分页合并，默认为false
	private String payerId = ""; // 付费用户ID
	private String exectInfo = ""; // 额外信息
	private String partnerId; // 合作方ID
	private String pkgId;//套餐ID
	private String qn = "1000";// 渠道号

	@Override
	public String toString() {
		return "TradeInfo [tradeId=" + tradeId + ", tradeName=" + tradeName
				+ ", tradeDesc=" + tradeDesc + ", reqFee=" + reqFee
				+ ", separable=" + separable + ", payerId=" + payerId
				+ ", exectInfo=" + exectInfo + ", partnerId=" + partnerId
				+ ", pkgId=" + pkgId + ", qn=" + qn + ", notifyUrl="
				+ notifyUrl + ", appId=" + appId + "]";
	}

	// 默认为宜搜的diz
	private String notifyUrl = "";
	private String appId;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	public String getReqFee() {
		return reqFee;
	}

	public void setReqFee(String reqFee) {
		this.reqFee = reqFee;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getExectInfo() {
		return exectInfo;
	}

	public void setExectInfo(String exectInfo) {
		this.exectInfo = exectInfo;
	}

	// 从配置文件中读取的信息
	public String getAppId() {
		// 应用ID由宜搜游戏平台提供
		return appId;
	}

	public String getPartenerId() {
		// 商户ID由宜搜游戏平台提供
		// return "1000100010001010";// 此处为测试ID
		return partnerId;
	}

	public String getNotifyUrl() {
		// TODO:通知接入方服务器交易结果，此处为测试地址
		// return "http://testservice1.pay.easou.com/service/test.e";
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getRedirectUrl() {
		// TODO:交易结束后重定向地址，重定向到接入方的支付结果页面，此处是测试地址。
		return "";
	}

	//
	// public String getSecertKey() {
	// // TODO：此处的SecretKey为开发测试使用的Key，正式接入时应向宜搜平台申请，由宜搜游戏平台提供
	// // return "8C97398507CCD24342C835D7AB09077D";
	// return ClientConfig.getProperty("secertKey");
	// }

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getSeparable() {
		if (separable)
			return "true";
		else
			return "false";
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPkgId() {
		return pkgId;
	}

	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}

}
