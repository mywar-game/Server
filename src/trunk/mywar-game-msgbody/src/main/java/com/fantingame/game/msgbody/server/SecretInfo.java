package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

import java.io.IOException;

/* 密钥消息体，cmdCode=5  */
public class SecretInfo implements ICodeAble {

	/**  加密算法文件名 **/
	private String secretId= new String() ;
	/**  服务器类型 0平台服务器  1战斗服务器  2排位赛服务器 **/
	private int serverType;

	public SecretInfo(){}

	public SecretInfo(String secretId , int serverType){
		this.secretId=secretId;
		this.serverType=serverType;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		secretId = dataInputStream.readUTF();
		serverType = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(secretId);
		dataOutputStream.writeInt(serverType);
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretId() {
		return secretId;
	}

	public void setServerType(int serverType) {
		this.serverType = serverType;
	}

	public int getServerType() {
		return serverType;
	}

}