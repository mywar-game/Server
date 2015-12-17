package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* ModifyUserPasswordTask请求消息体，cmdCode=6016  */
public class ReqModifyUserPassword implements ICodeAble {

	/**  玩家id **/
	private String userId= new String() ;
	/**  新密码 **/
	private String newPassword= new String() ;

	public ReqModifyUserPassword(){}

	public ReqModifyUserPassword(String userId , String newPassword){
		this.userId=userId;
		this.newPassword=newPassword;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		newPassword = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeUTF(newPassword);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

}