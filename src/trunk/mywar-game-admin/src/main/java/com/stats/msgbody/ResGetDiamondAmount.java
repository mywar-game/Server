package com.stats.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* GetDiamondAmountTask回复消息体，cmdCode=6102  */
public class ResGetDiamondAmount implements ICodeAble {

	/**  游戏服务器的钻石总数，Long型 **/
	private String serverDiamondAmount= new String() ;

	public ResGetDiamondAmount(){}

	public ResGetDiamondAmount(String serverDiamondAmount){
		this.serverDiamondAmount=serverDiamondAmount;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		serverDiamondAmount = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(serverDiamondAmount);
	}

	public void setServerDiamondAmount(String serverDiamondAmount) {
		this.serverDiamondAmount = serverDiamondAmount;
	}

	public String getServerDiamondAmount() {
		return serverDiamondAmount;
	}

}