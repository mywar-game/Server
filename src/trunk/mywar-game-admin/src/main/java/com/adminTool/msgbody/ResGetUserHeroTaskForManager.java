package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* GetUserHeroTaskForManager响应消息体,cmdCode = 6004  */
public class ResGetUserHeroTaskForManager implements ICodeAble {

	/** 用户英雄列表信息 **/
	private UnSynList<UserHeroSomeInfo> userHeroSomeInfoList = new UnSynList<UserHeroSomeInfo>();

	public ResGetUserHeroTaskForManager() {
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int userHeroSomeInfoListSize = dataInputStream.readInt();
		for (int i = 0; i < userHeroSomeInfoListSize; i++) {
			UserHeroSomeInfo t = new UserHeroSomeInfo();
//			t.decode(dataInputStream);
			userHeroSomeInfoList.add(t);
		}
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if (userHeroSomeInfoList != null) {
			dataOutputStream.writeInt(userHeroSomeInfoList.size());
			for (int i = 0, size = userHeroSomeInfoList.size(); i < size; i++) {
				UserHeroSomeInfo t = (UserHeroSomeInfo) userHeroSomeInfoList.get(i);
//				t.encode(dataOutputStream);
			}
		} else {
			dataOutputStream.writeInt(0);
		}
	}

	public void addUserHeroSomeInfoList(UserHeroSomeInfo value) {
		userHeroSomeInfoList.add(value);
	}

	public void delUserHeroSomeInfoList(int index) {
		userHeroSomeInfoList.remove(index);
	}

	public UserHeroSomeInfo getUserHeroSomeInfoList(int index) {
		return userHeroSomeInfoList.get(index);
	}

	public int getUserHeroSomeInfoListSize() {
		return userHeroSomeInfoList.size();
	}

	public UnSynList<UserHeroSomeInfo> getUserHeroSomeInfoList() {
		return userHeroSomeInfoList;
	}

	public void setUserHeroSomeInfoList(UnSynList<UserHeroSomeInfo> userHeroSomeInfoList) {
		this.userHeroSomeInfoList = userHeroSomeInfoList;
	}
}