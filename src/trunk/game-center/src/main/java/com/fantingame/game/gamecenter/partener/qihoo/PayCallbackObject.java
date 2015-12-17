package com.fantingame.game.gamecenter.partener.qihoo;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 360充值回调对象
 * @author chenjian
 *
 */
public class PayCallbackObject extends PaymentObj{
	private String app_key;
	private String product_id;
	private String amount;
	private String app_uid;
	private String app_ext1;
	private String app_ext2;
	private String user_id;
	private String order_id;
	private String gateway_flag;
	private String sign_type;
	private String app_order_id;
	private String sign_return;
	private String sign;

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getApp_uid() {
		return app_uid;
	}

	public void setApp_uid(String app_uid) {
		this.app_uid = app_uid;
	}

	public String getApp_ext1() {
		return app_ext1;
	}

	public void setApp_ext1(String app_ext1) {
		this.app_ext1 = app_ext1;
	}

	public String getApp_ext2() {
		return app_ext2;
	}

	public void setApp_ext2(String app_ext2) {
		this.app_ext2 = app_ext2;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getGateway_flag() {
		return gateway_flag;
	}

	public void setGateway_flag(String gateway_flag) {
		this.gateway_flag = gateway_flag;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getApp_order_id() {
		return app_order_id;
	}

	public void setApp_order_id(String app_order_id) {
		this.app_order_id = app_order_id;
	}

	public String getSign_return() {
		return sign_return;
	}

	public void setSign_return(String sign_return) {
		this.sign_return = sign_return;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
