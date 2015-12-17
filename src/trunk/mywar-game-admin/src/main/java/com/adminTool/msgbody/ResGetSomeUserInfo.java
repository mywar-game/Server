package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;

import java.io.IOException;

/* GetSomeUserInfoTask回复消息体，cmdCode=6027  */
public class ResGetSomeUserInfo implements ICodeAble {

	/**  用户某些信息 **/
	private UnSynList<UserSomeInfo> UserSomeInfoList= new UnSynList<UserSomeInfo>() ;

	public ResGetSomeUserInfo(){}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int UserSomeInfoListSize = dataInputStream.readInt();
		for(int i=0;i<UserSomeInfoListSize;i++)
		{
			UserSomeInfo t = new  UserSomeInfo();
//			t.decode(dataInputStream);
			getUserSomeInfoList().add(t);
		}

	}

	@SuppressWarnings("unused")
	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(getUserSomeInfoList()!=null)
		{
			dataOutputStream.writeInt(getUserSomeInfoList().size());
			for(int i=0,size=getUserSomeInfoList().size();i<size;i++)
			{
				UserSomeInfo t= (UserSomeInfo)getUserSomeInfoList().get(i);
//				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void addUserSomeInfoList(UserSomeInfo value ) {
		getUserSomeInfoList().add(value);
	}

	public void delUserSomeInfoList(int index) {
		getUserSomeInfoList().remove(index);
	}

	public UserSomeInfo getUserSomeInfoList(int index) {
		return UserSomeInfoList.get(index);
	}

	public int getUserSomeInfoListSize() {
		return getUserSomeInfoList().size();
	}

	public void setUserSomeInfoList(UnSynList<UserSomeInfo> userSomeInfoList) {
		UserSomeInfoList = userSomeInfoList;
	}

	public UnSynList<UserSomeInfo> getUserSomeInfoList() {
		return UserSomeInfoList;
	}

}