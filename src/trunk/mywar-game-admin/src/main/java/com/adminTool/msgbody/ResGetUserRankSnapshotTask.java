package com.adminTool.msgbody;

import java.io.IOException;

import com.adminTool.bean.UserRankSnapshot;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.UnSynList;

/* GetUserRankSnapshotTask回复消息体，cmdCode=6020  */
public class ResGetUserRankSnapshotTask implements ICodeAble {

	/**
	 * @return the userRankSnapshotList
	 */
	public UnSynList<UserRankSnapshot> getUserRankSnapshotList() {
		return userRankSnapshotList;
	}

	/**
	 * @param userRankSnapshotList the userRankSnapshotList to set
	 */
	public void setUserRankSnapshotList(
			UnSynList<UserRankSnapshot> userRankSnapshotList) {
		this.userRankSnapshotList = userRankSnapshotList;
	}

	/**  玩家排行快照列表 **/
	private UnSynList<UserRankSnapshot> userRankSnapshotList= new UnSynList<UserRankSnapshot>() ;

	public ResGetUserRankSnapshotTask(){}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int userRankSnapshotListSize = dataInputStream.readInt();
		for(int i=0;i<userRankSnapshotListSize;i++)
		{
			UserRankSnapshot t = new  UserRankSnapshot();
			t.decode(dataInputStream);
			userRankSnapshotList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(userRankSnapshotList!=null)
		{
			dataOutputStream.writeInt(userRankSnapshotList.size());
			for(int i=0,size=userRankSnapshotList.size();i<size;i++)
			{
				UserRankSnapshot t= (UserRankSnapshot)userRankSnapshotList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void addUserRankSnapshotList(UserRankSnapshot value ) {
		userRankSnapshotList.add(value);
	}

	public void delUserRankSnapshotList(int index) {
		userRankSnapshotList.remove(index);
	}

	public UserRankSnapshot getUserRankSnapshotList(int index) {
		return userRankSnapshotList.get(index);
	}

	public int getUserRankSnapshotListSize() {
		return userRankSnapshotList.size();
	}

}