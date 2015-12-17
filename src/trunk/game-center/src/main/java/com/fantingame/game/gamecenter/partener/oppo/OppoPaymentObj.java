package com.fantingame.game.gamecenter.partener.oppo;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * Oppo支付回调对象
 * 
 * @author yezp
 */
public class OppoPaymentObj extends PaymentObj {

	/**
	 * 回调通知ID（该值使用系统为这次支付生成的订单号）
	 */
	private String notifyId;

	/**
	 * 订单号
	 */
	private String partnerOrder;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品描述
	 */
	private String productDesc;

	/**
	 * 商品价格(以分为单位)
	 */
	private int price;

	/**
	 * 商品数量
	 */
	private int count;

	/**
	 * 请求支付时上传的附加参数（客户端上传）
	 */
	private String attach;

	/**
	 * 签名
	 */
	private String sign;

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getPartnerOrder() {
		return partnerOrder;
	}

	public void setPartnerOrder(String partnerOrder) {
		this.partnerOrder = partnerOrder;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
