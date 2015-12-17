package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;
import com.adminTool.bean.UserTechnologyInfo;

/* GetUserTechnologyInfoTask回复消息体，cmdCode=6019  */
public class ResGetUserTechnologyInfo implements ICodeAble {

	/**  玩家科技信息 **/
	private UnSynList<UserTechnologyInfo> UserTechnologyInfoList= new UnSynList<UserTechnologyInfo>() ;

	public ResGetUserTechnologyInfo(){}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int UserTechnologyInfoListSize = dataInputStream.readInt();
		for(int i=0;i<UserTechnologyInfoListSize;i++)
		{
			UserTechnologyInfo t = new  UserTechnologyInfo();
			t.decode(dataInputStream);
			UserTechnologyInfoList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(UserTechnologyInfoList!=null)
		{
			dataOutputStream.writeInt(UserTechnologyInfoList.size());
			for(int i=0,size=UserTechnologyInfoList.size();i<size;i++)
			{
				UserTechnologyInfo t= (UserTechnologyInfo)UserTechnologyInfoList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	/**
	 * @return the userTechnologyInfoList
	 */
	public UnSynList<UserTechnologyInfo> getUserTechnologyInfoList() {
		return UserTechnologyInfoList;
	}

	/**
	 * @param userTechnologyInfoList the userTechnologyInfoList to set
	 */
	public void setUserTechnologyInfoList(
			UnSynList<UserTechnologyInfo> userTechnologyInfoList) {
		UserTechnologyInfoList = userTechnologyInfoList;
	}

	public void addUserTechnologyInfoList(UserTechnologyInfo value ) {
		UserTechnologyInfoList.add(value);
	}

	public void delUserTechnologyInfoList(int index) {
		UserTechnologyInfoList.remove(index);
	}

	public UserTechnologyInfo getUserTechnologyInfoList(int index) {
		return UserTechnologyInfoList.get(index);
	}

	public int getUserTechnologyInfoListSize() {
		return UserTechnologyInfoList.size();
	}

}