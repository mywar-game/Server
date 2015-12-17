package com.dataconfig.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * PaymentLog entity. @author MyEclipse Persistence Tools
 */

public class PaymentLog implements java.io.Serializable {

	// Fields

	private Integer paymentLogId;
	private String partnerId;
	private String serverId;
	private String partnerUserId;
	private String userId;
	private String userName;
	private Integer userLevel;
	private String channel;
	private String orderId;
	private Double amount;
	private Integer gold;
	private String userIp;
	private String remark;
	private Timestamp createdTime;
	/**玩家标识**/
	private int lodoId;
	
	// add
	private String partnerName;
	private String serverName;

	// Constructors

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/** default constructor */
	public PaymentLog() {
	}

	/** minimal constructor */
	public PaymentLog(String partnerId, String serverId, String partnerUserId,
			String userId, String userName, Integer userLevel, String channel,
			String orderId, Double amount, Integer gold, Timestamp createdTime) {
		this.partnerId = partnerId;
		this.serverId = serverId;
		this.partnerUserId = partnerUserId;
		this.userId = userId;
		this.userName = userName;
		this.userLevel = userLevel;
		this.channel = channel;
		this.orderId = orderId;
		this.amount = amount;
		this.gold = gold;
		this.createdTime = createdTime;
	}

	/** full constructor */
	public PaymentLog(String partnerId, String serverId, String partnerUserId,
			String userId, String userName, Integer userLevel, String channel,
			String orderId, Double amount, Integer gold, String userIp,
			String remark, Timestamp createdTime) {
		this.partnerId = partnerId;
		this.serverId = serverId;
		this.partnerUserId = partnerUserId;
		this.userId = userId;
		this.userName = userName;
		this.userLevel = userLevel;
		this.channel = channel;
		this.orderId = orderId;
		this.amount = amount;
		this.gold = gold;
		this.userIp = userIp;
		this.remark = remark;
		this.createdTime = createdTime;
	}
	
	public PaymentLog(String userId, Integer userLevel, String orderId, String channel, Double amount, Date payTime,String userName, int lodoId){
		this.userId = userId;
		this.userLevel = userLevel;
		this.orderId = orderId;
		this.partnerId = channel;
		this.amount = amount;
		this.createdTime = new Timestamp(payTime.getTime());
		this.userName = userName;
		this.lodoId = lodoId;
	}

	// Property accessors

	public Integer getPaymentLogId() {
		return this.paymentLogId;
	}

	public void setPaymentLogId(Integer paymentLogId) {
		this.paymentLogId = paymentLogId;
	}


	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getPartnerUserId() {
		return this.partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getGold() {
		return this.gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}