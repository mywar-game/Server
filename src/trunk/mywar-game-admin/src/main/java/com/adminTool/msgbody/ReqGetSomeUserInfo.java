package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* GetSomeUserInfoTask请求消息体,cmdCode=6027  */
public class ReqGetSomeUserInfo implements ICodeAble {

	/**  userIds **/
	private String userIds= new String() ;

	public ReqGetSomeUserInfo(){}

	public ReqGetSomeUserInfo(String userIds){
		this.userIds=userIds;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userIds = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userIds);
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserIds() {
		return userIds;
	}

}