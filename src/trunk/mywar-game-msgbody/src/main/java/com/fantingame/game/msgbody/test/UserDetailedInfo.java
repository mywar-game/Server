package com.fantingame.game.msgbody.test;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

/* 测试消息体  */
public class UserDetailedInfo implements ICodeAble {

	/**  test **/
	private String userName= new String() ;
	/**  test **/
	private int sex;

	public UserDetailedInfo(){}

	public UserDetailedInfo(String userName , int sex){
		this.userName=userName;
		this.sex=sex;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userName = dataInputStream.readUTF();
		sex = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userName);
		dataOutputStream.writeInt(sex);
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getSex() {
		return sex;
	}

}