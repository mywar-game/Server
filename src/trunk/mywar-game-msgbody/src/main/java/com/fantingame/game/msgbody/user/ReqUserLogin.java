package com.fantingame.game.msgbody.user;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqUserLogin implements ICodeAble {
    private String token;
    private String userId;
    private String partnerId;
    private String serverId;
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(token);
		outputStream.writeUTF(userId);
		outputStream.writeUTF(partnerId);
		outputStream.writeUTF(serverId);
	}
	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		token = inputStream.readUTF();
		userId = inputStream.readUTF();
		partnerId = inputStream.readUTF();
		serverId = inputStream.readUTF();
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	
}
