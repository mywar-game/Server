package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* 请求获得在线玩家数  */
public class ResGetOnlineUserAmount implements ICodeAble {

	/**  在线玩家数 **/
	private int onlineUserAmount;

	public ResGetOnlineUserAmount() {
	}

	public ResGetOnlineUserAmount(int onlineUserAmount) {
		this.onlineUserAmount = onlineUserAmount;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		onlineUserAmount = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(onlineUserAmount);
	}

	public void setOnlineUserAmount(int onlineUserAmount) {
		this.onlineUserAmount = onlineUserAmount;
	}

	public int getOnlineUserAmount() {
		return onlineUserAmount;
	}

}