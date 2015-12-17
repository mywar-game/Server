package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* 后台管理生成测试账号返回消息体cmdCode=6014  */
public class ResGenerateTestAccount implements ICodeAble {

	/**  用户账号信息 **/
	private UnSynList<UserAccountInfo> accountList= new UnSynList<UserAccountInfo>() ;

	public UnSynList<UserAccountInfo> getAccountList() {
		return accountList;
	}

	public void setAccountList(UnSynList<UserAccountInfo> accountList) {
		this.accountList = accountList;
	}

	public ResGenerateTestAccount(){}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int accountListSize = dataInputStream.readInt();
		for(int i=0;i<accountListSize;i++)
		{
			UserAccountInfo t = new  UserAccountInfo();
			t.decode(dataInputStream);
			accountList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(accountList!=null)
		{
			dataOutputStream.writeInt(accountList.size());
			for(int i=0,size=accountList.size();i<size;i++)
			{
				UserAccountInfo t= (UserAccountInfo)accountList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void addAccountList(UserAccountInfo value ) {
		accountList.add(value);
	}

	public void delAccountList(int index) {
		accountList.remove(index);
	}

	public UserAccountInfo getAccountList(int index) {
		return accountList.get(index);
	}

	public int getAccountListSize() {
		return accountList.size();
	}

}