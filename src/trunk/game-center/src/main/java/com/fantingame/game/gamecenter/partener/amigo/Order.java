package com.fantingame.game.gamecenter.partener.amigo;

import java.math.BigDecimal;

public class Order {
	// 商户订单号
	private final String outOrderNo;
	// 玩家playerId
	private final String playerId;
	// 商品名称
	private final String subject;
	// 商户apiKey
	private final String apiKey;
	// 需支付金额，dealPrice值需要等于totalFee
	private final BigDecimal totalFee;
	// 商户总金额，dealPrice值需要等于totalFee
	private final BigDecimal dealPrice;
	// 订单提交时间，格式为yyyyMMddHHmmss，由商户服务器提供，客户端调起支付收银台时需要使用这个值
	private final String submitTime;
	// 订单失效时间，可选，格式为yyyyMMddHHmmss，如果有值必须参与签名
	private final String expireTime;
	// 服务器通知地址，可选，不能超过1024个字符，如果有该字段，必须参与签名
	private final String notifyURL;

	/**
	 * @param outOrderNo
	 *            商户订单号 [必填]
	 * @param playerId
	 *            玩家playerId [必填]
	 * @param subject
	 *            商品名称 [必填]
	 * @param apiKey
	 *            商户apiKey [必填]
	 * @param totalFee
	 *            需支付金额，dealPrice值需要等于totalFee [必填]
	 * @param dealPrice
	 *            商户总金额，dealPrice值需要等于totalFee [必填]
	 * @param submitTime
	 *            订单提交时间，格式为yyyyMMddHHmmss，由商户服务器提供，客户端调起支付收银台时需要使用这个值 [必填]
	 * @param expireTime
	 *            订单失效时间，可选，格式为yyyyMMddHHmmss，如果有值必须参与签名 [可选]
	 * @param notifyURL
	 *            服务器通知地址，不能超过1024个字符，如果有该字段，必须参与签名(如果商户需要自定义参数，可以在创建订单时以
	 *            "http://www.partner.com/notifyReceiver?param1=value1&param2=value2"
	 *            的形式定义url) [可选]
	 */
	public Order(String outOrderNo, String playerId, String subject, String apiKey, BigDecimal totalFee,
			BigDecimal dealPrice, String submitTime, String expireTime, String notifyURL) {
		this.outOrderNo = outOrderNo;
		this.playerId = playerId;
		this.subject = subject;
		this.apiKey = apiKey;
		this.totalFee = totalFee;
		this.dealPrice = dealPrice;
		this.submitTime = submitTime;
		this.expireTime = expireTime;
		this.notifyURL = notifyURL;
	}

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public String getPlayerId() {
		return playerId;
	}

	public String getSubject() {
		return subject;
	}

	public String getApiKey() {
		return apiKey;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public BigDecimal getDealPrice() {
		return dealPrice;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public String getNotifyURL() {
		return notifyURL;
	}

	@Override
	public String toString() {
		return "Order [outOrderNo=" + outOrderNo + ", playerId=" + playerId + ", subject=" + subject + ", apiKey="
				+ apiKey + ", totalFee=" + totalFee + ", dealPrice=" + dealPrice + ", submitTime=" + submitTime
				+ ", expireTime=" + expireTime + ", notifyURL=" + notifyURL + "]";
	}
}
