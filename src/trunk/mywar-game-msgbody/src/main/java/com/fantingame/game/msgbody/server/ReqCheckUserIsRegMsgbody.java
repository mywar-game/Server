package com.fantingame.game.msgbody.server;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqCheckUserIsRegMsgbody implements ICodeAble {
	/**
	 * 合作厂商用户ID
	 */
	private String partnerUserId;
	/**
	 * 服务器ID
	 */
	private String serverId;
	/**
	 * 登录时间
	 */
	private long loginTime;
	/**
	 * md5str
	 */
	private String md5Str;
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(partnerUserId);
		outputStream.writeUTF(serverId);
		outputStream.writeLong(loginTime);
		outputStream.writeUTF(md5Str);
	}
	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		partnerUserId = inputStream.readUTF();
		serverId = inputStream.readUTF();
		loginTime = inputStream.readLong();
		md5Str = inputStream.readUTF();
	}
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
}
