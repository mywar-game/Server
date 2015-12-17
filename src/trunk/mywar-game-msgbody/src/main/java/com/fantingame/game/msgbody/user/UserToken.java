package com.fantingame.game.msgbody.user;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class UserToken implements ICodeAble{
	/**
	 * 用户登陆令牌
	 */
	private String token="";
	
	/**
	 * 合作商户
	 */
	private String partnerToken="";
	
	/**
	 * 合作厂商用户ID
	 */
	private String partnerUserId="";
	/**
	 * 合作厂商ID
	 */
	private String partnerId="";
	
	/**
	 * 服务器ID
	 */
	private String serverId="";
	
	/**
	 * 额外附加字段，用于兼容不同平台的额外的字段需求
	 */
	private String extInfo="";
	/**
	 * 游戏用户ID
	 */
	private String userId="";
	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(token);
		outputStream.writeUTF(partnerToken);;
		outputStream.writeUTF(partnerUserId);;
		outputStream.writeUTF(partnerId);;
		outputStream.writeUTF(serverId);;
		outputStream.writeUTF(extInfo);;
		outputStream.writeUTF(userId);;
		
	}
	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		token = inputStream.readUTF();
        partnerToken = inputStream.readUTF();
        partnerUserId = inputStream.readUTF();
        partnerId = inputStream.readUTF();
        serverId = inputStream.readUTF();
        extInfo = inputStream.readUTF();
        userId = inputStream.readUTF();
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
}
