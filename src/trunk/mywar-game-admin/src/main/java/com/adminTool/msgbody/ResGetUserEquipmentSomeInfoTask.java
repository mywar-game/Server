package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* GetUserEquipmentTaskForManager响应消息体,cmdCode = 6005  */
public class ResGetUserEquipmentSomeInfoTask implements ICodeAble {

	/** 用户装备列表信息 **/
	private UnSynList<UserEquipmentSomeInfo> userEquipmentSomeInfoList = new UnSynList<UserEquipmentSomeInfo>();

	public ResGetUserEquipmentSomeInfoTask() {
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int userEquipmentSomeInfoListSize = dataInputStream.readInt();
		for (int i = 0; i < userEquipmentSomeInfoListSize; i++) {
			UserEquipmentSomeInfo t = new UserEquipmentSomeInfo();
//			t.decode(dataInputStream);
			userEquipmentSomeInfoList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if (userEquipmentSomeInfoList != null) {
			dataOutputStream.writeInt(userEquipmentSomeInfoList.size());
			for (int i = 0, size = userEquipmentSomeInfoList.size(); i < size; i++) {
				UserEquipmentSomeInfo t = (UserEquipmentSomeInfo) userEquipmentSomeInfoList.get(i);
//				t.encode(dataOutputStream);
			}
		} else {
			dataOutputStream.writeInt(0);
		}

	}

	public void addUserEquipmentSomeInfoList(UserEquipmentSomeInfo value) {
		userEquipmentSomeInfoList.add(value);
	}

	public void delUserEquipmentSomeInfoList(int index) {
		userEquipmentSomeInfoList.remove(index);
	}

	public UserEquipmentSomeInfo getUserEquipmentSomeInfoList(int index) {
		return userEquipmentSomeInfoList.get(index);
	}

	public int getUserEquipmentSomeInfoListSize() {
		return userEquipmentSomeInfoList.size();
	}

	/**
	 * 获取 用户装备列表信息 
	 */
	public UnSynList<UserEquipmentSomeInfo> getUserEquipmentSomeInfoList() {
		return userEquipmentSomeInfoList;
	}

	/**
	 * 设置 用户装备列表信息 
	 */
	public void setUserEquipmentSomeInfoList(
			UnSynList<UserEquipmentSomeInfo> userEquipmentSomeInfoList) {
		this.userEquipmentSomeInfoList = userEquipmentSomeInfoList;
	}

}