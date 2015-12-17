package com.adminTool.msgbody;

import java.io.IOException;

import com.adminTool.bean.UserMail;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* 发送系统邮件请求消息体,cmdCode = 6007  */
public class ReqSendSystemMail implements ICodeAble {

	/** 附件 **/
	private String mailAttach = new String();
	/** 系统邮件列表 **/
	private UnSynList<UserMail> userMailList = new UnSynList<UserMail>();

	public ReqSendSystemMail() {
	}

	public ReqSendSystemMail(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		mailAttach = dataInputStream.readUTF();
		int userMailListSize = dataInputStream.readInt();
		for (int i = 0; i < userMailListSize; i++) {
			UserMail t = new UserMail();
			t.decode(dataInputStream);
			userMailList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(mailAttach);
		if (userMailList != null) {
			dataOutputStream.writeInt(userMailList.size());
			for (int i = 0, size = userMailList.size(); i < size; i++) {
				UserMail t = (UserMail) userMailList.get(i);
				t.encode(dataOutputStream);
			}
		} else {
			dataOutputStream.writeInt(0);
		}

	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public String getMailAttach() {
		return mailAttach;
	}

	public void addUserMailList(UserMail value) {
		userMailList.add(value);
	}

	public void delUserMailList(int index) {
		userMailList.remove(index);
	}

	public UserMail getUserMailList(int index) {
		return userMailList.get(index);
	}

	public int getUserMailListSize() {
		return userMailList.size();
	}

	public UnSynList<UserMail> getUserMailList() {
		return userMailList;
	}

	public void setUserMailList(UnSynList<UserMail> userMailList) {
		this.userMailList = userMailList;
	}

}