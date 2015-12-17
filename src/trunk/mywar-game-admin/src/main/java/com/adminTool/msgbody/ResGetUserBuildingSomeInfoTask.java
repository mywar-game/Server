package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* GetUserBuildingTaskForManager响应消息体,cmdCode = 6003  */
public class ResGetUserBuildingSomeInfoTask implements ICodeAble {

	/** 用户建筑列表信息 **/
	private UnSynList<UserBuildingSomeInfo> userBuildingSomeInfoList = new UnSynList<UserBuildingSomeInfo>();

	public ResGetUserBuildingSomeInfoTask() {
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int userBuildingSomeInfoListSize = dataInputStream.readInt();
		for (int i = 0; i < userBuildingSomeInfoListSize; i++) {
			UserBuildingSomeInfo t = new UserBuildingSomeInfo();
			t.decode(dataInputStream);
			userBuildingSomeInfoList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if (userBuildingSomeInfoList != null) {
			dataOutputStream.writeInt(userBuildingSomeInfoList.size());
			for (int i = 0, size = userBuildingSomeInfoList.size(); i < size; i++) {
				UserBuildingSomeInfo t = (UserBuildingSomeInfo) userBuildingSomeInfoList.get(i);
				t.encode(dataOutputStream);
			}
		} else {
			dataOutputStream.writeInt(0);
		}

	}

	public void addUserBuildingSomeInfoList(UserBuildingSomeInfo value) {
		userBuildingSomeInfoList.add(value);
	}

	public void delUserBuildingSomeInfoList(int index) {
		userBuildingSomeInfoList.remove(index);
	}

	public UserBuildingSomeInfo getUserBuildingSomeInfoList(int index) {
		return userBuildingSomeInfoList.get(index);
	}

	public int getUserBuildingListSize() {
		return userBuildingSomeInfoList.size();
	}

	public UnSynList<UserBuildingSomeInfo> getUserBuildingSomeInfoList() {
		return userBuildingSomeInfoList;
	}

	public void setUserBuildingSomeInfoList(UnSynList<UserBuildingSomeInfo> userBuildingSomeInfoList) {
		this.userBuildingSomeInfoList = userBuildingSomeInfoList;
	}

}