package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* GetUserRankSnapshotTask请求消息体，cmdCode=6020  */
public class ReqGetUserRankSnapshotTask implements ICodeAble {

	/**  排行快照的类型 **/
	private int snapshotType;

	public ReqGetUserRankSnapshotTask(){}

	public ReqGetUserRankSnapshotTask(int snapshotType){
		this.snapshotType=snapshotType;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		snapshotType = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(snapshotType);
	}

	public void setSnapshotType(int snapshotType) {
		this.snapshotType = snapshotType;
	}

	public int getSnapshotType() {
		return snapshotType;
	}

}