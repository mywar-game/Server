package com.adminTool.msgbody;

import java.io.IOException;
import com.adminTool.bean.UserTreasureQueue;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* GetUserTreasureTaskForManager响应消息体,cmdCode=6006  */
public class ResGetUserTreasureTaskForManager implements ICodeAble {

	/**  用户道具列表信息 **/
	private UnSynList<UserTreasureInfo> userTreasureList= new UnSynList<UserTreasureInfo>() ;
	/**  用户道具队列列表信息 **/
	private UnSynList<UserTreasureQueue> userTreasureQueueList= new UnSynList<UserTreasureQueue>() ;

	public ResGetUserTreasureTaskForManager(){}

	/**
	 * @return the userTreasureList
	 */
	public UnSynList<UserTreasureInfo> getUserTreasureList() {
		return userTreasureList;
	}

	/**
	 * @param userTreasureList the userTreasureList to set
	 */
	public void setUserTreasureList(UnSynList<UserTreasureInfo> userTreasureList) {
		this.userTreasureList = userTreasureList;
	}

	/**
	 * @return the userTreasureQueueList
	 */
	public UnSynList<UserTreasureQueue> getUserTreasureQueueList() {
		return userTreasureQueueList;
	}

	/**
	 * @param userTreasureQueueList the userTreasureQueueList to set
	 */
	public void setUserTreasureQueueList(
			UnSynList<UserTreasureQueue> userTreasureQueueList) {
		this.userTreasureQueueList = userTreasureQueueList;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int userTreasureListSize = dataInputStream.readInt();
		for(int i=0;i<userTreasureListSize;i++)
		{
			UserTreasureInfo t = new  UserTreasureInfo();
//			t.decode(dataInputStream);
			userTreasureList.add(t);
		}

		int userTreasureQueueListSize = dataInputStream.readInt();
		for(int i=0;i<userTreasureQueueListSize;i++)
		{
			UserTreasureQueue t = new  UserTreasureQueue();
			t.decode(dataInputStream);
			userTreasureQueueList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(userTreasureList!=null)
		{
			dataOutputStream.writeInt(userTreasureList.size());
			for(int i=0,size=userTreasureList.size();i<size;i++)
			{
				UserTreasureInfo t= (UserTreasureInfo)userTreasureList.get(i);
//				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

		if(userTreasureQueueList!=null)
		{
			dataOutputStream.writeInt(userTreasureQueueList.size());
			for(int i=0,size=userTreasureQueueList.size();i<size;i++)
			{
				UserTreasureQueue t= (UserTreasureQueue)userTreasureQueueList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void addUserTreasureList(UserTreasureInfo value ) {
		userTreasureList.add(value);
	}

	public void delUserTreasureList(int index) {
		userTreasureList.remove(index);
	}

	public UserTreasureInfo getUserTreasureList(int index) {
		return userTreasureList.get(index);
	}

	public int getUserTreasureListSize() {
		return userTreasureList.size();
	}

	public void addUserTreasureQueueList(UserTreasureQueue value ) {
		userTreasureQueueList.add(value);
	}

	public void delUserTreasureQueueList(int index) {
		userTreasureQueueList.remove(index);
	}

	public UserTreasureQueue getUserTreasureQueueList(int index) {
		return userTreasureQueueList.get(index);
	}

	public int getUserTreasureQueueListSize() {
		return userTreasureQueueList.size();
	}

}