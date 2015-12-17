package com.adminTool.bean;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 请求改变玩家账号类型  */
public class ReqChangeUserType implements ICodeAble {

	/**  玩家ID **/
	private String userID= new String() ;
	/**  账号类型 **/
	private int type;

	public ReqChangeUserType(){}

	public ReqChangeUserType(String userID , int type){
		this.userID=userID;
		this.type=type;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userID = dataInputStream.readUTF();
		type = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userID);
		dataOutputStream.writeInt(type);
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}