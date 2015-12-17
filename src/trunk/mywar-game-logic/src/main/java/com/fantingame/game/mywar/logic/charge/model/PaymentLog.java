package com.fantingame.game.mywar.logic.charge.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值日志日志
 * 
 * 
 */
public class PaymentLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 合作商ID
	 */
	private String partnerId;

	/**
	 * 服务器ID
	 */
	private String serverId;

	/**
	 * 合作商用户ID
	 */
	private String partnerUserId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 渠道
	 */
	private String channel;

	/**
	 * 订单ID
	 */
	private String orderId;

	/**
	 * 充值金额
	 */
	private BigDecimal amount;

	/**
	 * 充值金币
	 */
	private int gold;

	/**
	 * 用户IP
	 */
	private String userIp;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createdTime;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getPartnerUserId() {
		return partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}