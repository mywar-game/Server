package com.fantingame.game.msgbody.server;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqConnectToLoginServer implements ICodeAble {

	private boolean isReconnect;
//	private LocalConfig localConfig = new LocalConfig();
	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		isReconnect = inputStream.readBoolean();
//		localConfig.decode(inputStream);
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		outputStream.writeBoolean(isReconnect);
//		localConfig.encode(outputStream);
	}

	public boolean isReconnect() {
		return isReconnect;
	}

	public void setReconnect(boolean isReconnect) {
		this.isReconnect = isReconnect;
	}

//	public LocalConfig getLocalConfig() {
//		return localConfig;
//	}
//
//	public void setLocalConfig(LocalConfig localConfig) {
//		this.localConfig = localConfig;
//	}

}
