package com.fantingame.game.msgbody.user;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqCreatUser implements ICodeAble {

	private String username;
	private String partnerId;
	private String qn;
	private String paternUserId;
	private String paternUserName;
	private String serverId;
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(username);
		outputStream.writeUTF(partnerId);
		outputStream.writeUTF(qn);
		outputStream.writeUTF(paternUserId);
		outputStream.writeUTF(paternUserName);
		outputStream.writeUTF(serverId);
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		username = inputStream.readUTF();
		partnerId = inputStream.readUTF();
		qn = inputStream.readUTF();
		paternUserId = inputStream.readUTF();
		paternUserName = inputStream.readUTF();
		serverId   = inputStream.readUTF();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getPaternUserId() {
		return paternUserId;
	}

	public void setPaternUserId(String paternUserId) {
		this.paternUserId = paternUserId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getPaternUserName() {
		return paternUserName;
	}

	public void setPaternUserName(String paternUserName) {
		this.paternUserName = paternUserName;
	}

}
