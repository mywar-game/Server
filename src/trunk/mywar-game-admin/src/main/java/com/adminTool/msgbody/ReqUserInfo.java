package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* GetUserInfoTask请求消息体,cmdCode = 6002  */
public class ReqUserInfo implements ICodeAble {

	/** userId **/
	private String userId = new String();

	public ReqUserInfo() {
	}

	public ReqUserInfo(String userId) {
		this.userId = userId;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

}