package com.fantingame.game.msgbody.user;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqPament implements ICodeAble{
	private String partnerId="";
	private String serverId="";
	private String partnerUserId="";
	private String channel="";
	private String orderId="";
	private String amount="";
	private String gold="";
	private String userIp="";
	private String remark="";
	
	public ReqPament() {
		super();
	}
	public ReqPament(String partnerId, String serverId, String partnerUserId,
			String channel, String orderId, String amount, String gold,
			String userIp, String remark) {
		super();
		this.partnerId = partnerId;
		this.serverId = serverId;
		this.partnerUserId = partnerUserId;
		this.channel = channel;
		this.orderId = orderId;
		this.amount = amount;
		this.gold = gold;
		this.userIp = userIp;
		this.remark = remark;
	}
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(partnerId);
		outputStream.writeUTF(serverId);
		outputStream.writeUTF(partnerUserId);
		outputStream.writeUTF(channel);
		outputStream.writeUTF(orderId);
		outputStream.writeUTF(amount);
		outputStream.writeUTF(gold);
		outputStream.writeUTF(userIp);
		outputStream.writeUTF(remark);
	}
	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		partnerId = inputStream.readUTF();
		serverId = inputStream.readUTF();
		partnerUserId = inputStream.readUTF();
		channel = inputStream.readUTF();
		orderId = inputStream.readUTF();
		amount = inputStream.readUTF();
		gold = inputStream.readUTF();
		userIp = inputStream.readUTF();
		remark = inputStream.readUTF();
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
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
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
}
