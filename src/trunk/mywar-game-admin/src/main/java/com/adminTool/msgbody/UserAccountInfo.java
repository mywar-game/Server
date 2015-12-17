package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 用户账号信息  */
public class UserAccountInfo implements ICodeAble {

	/**  用户id **/
	private String userId= new String() ;
	/**  用户名 **/
	private String userName= new String() ;
	/**  密码 **/
	private String password= new String() ;
	/**  生成时间 **/
	private String createTime= new String() ;

	public UserAccountInfo(){}

	public UserAccountInfo(String userId , String userName , String password , String createTime){
		this.userId=userId;
		this.userName=userName;
		this.password=password;
		this.createTime=createTime;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		userName = dataInputStream.readUTF();
		password = dataInputStream.readUTF();
		createTime = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeUTF(userName);
		dataOutputStream.writeUTF(password);
		dataOutputStream.writeUTF(createTime);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

}