package com.fantingame.game.msgbody.server;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqUserLoginOutForServer implements ICodeAble {

	private String userId;
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);
	}
	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
