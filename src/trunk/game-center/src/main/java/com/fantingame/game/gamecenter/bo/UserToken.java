package com.fantingame.game.gamecenter.bo;

public class UserToken {
	/**
	 * 用户登陆令牌
	 */
	private String Token;
	
	/**
	 * 合作商户
	 */
	private String partnerToken;
	
	/**
	 * 合作厂商用户ID
	 */
	private String partnerUserId;
	/**
	 * 合作厂商ID
	 */
	private String partnerId;
	
	/**
	 * 服务器ID
	 */
	private String serverId;
	
	/**
	 * 额外附加字段，用于兼容不同平台的额外的字段需求
	 */
	private String extInfo;
	/**
	 * 游戏用户ID
	 */
	private String userId;
	
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPartnerToken() {
		return partnerToken;
	}
	public void setPartnerToken(String partnerToken) {
		this.partnerToken = partnerToken;
	}
	public String getExtInfo() {
		return extInfo;
	}
	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}
	
	@Override
	public String toString() {
		return "Token=" + Token + " partnerToken=" + partnerToken + " partnerUserId=" + partnerUserId + " partnerId=" + partnerId + " serverId=" + serverId + " extInfo=" + extInfo + " userId=" + userId;
	}
}
