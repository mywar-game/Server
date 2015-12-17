package com.adminTool.msgbody;

import java.io.IOException;

import com.adminTool.bean.UserBean;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* GetHugeDiamondUserListTask回复消息体，cmdCode=6017  */
public class ResGetHugeDiamondUserList implements ICodeAble {

	/**  巨额钻石用户列表 **/
	private UnSynList<UserBean> userBeanList= new UnSynList<UserBean>() ;

	public ResGetHugeDiamondUserList(){}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int userBeanListSize = dataInputStream.readInt();
		for(int i=0;i<userBeanListSize;i++)
		{
			UserBean t = new  UserBean();
			t.decode(dataInputStream);
			userBeanList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(userBeanList!=null)
		{
			dataOutputStream.writeInt(userBeanList.size());
			for(int i=0,size=userBeanList.size();i<size;i++)
			{
				UserBean t= (UserBean)userBeanList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void addUserBeanList(UserBean value ) {
		userBeanList.add(value);
	}

	public void delUserBeanList(int index) {
		userBeanList.remove(index);
	}

	public UserBean getUserBeanList(int index) {
		return userBeanList.get(index);
	}

	public int getUserBeanListSize() {
		return userBeanList.size();
	}

	/**
	 * @return the userBeanList
	 */
	public UnSynList<UserBean> getUserBeanList() {
		return userBeanList;
	}

	/**
	 * @param userBeanList the userBeanList to set
	 */
	public void setUserBeanList(UnSynList<UserBean> userBeanList) {
		this.userBeanList = userBeanList;
	}

}