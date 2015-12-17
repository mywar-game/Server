package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 替换玩家当前任务请求消息体cmdCode=6020  */
public class ReqReplaceUserCurrentMission implements ICodeAble {

	/**  任务常量编号 **/
	private int missionId;
	
	private String userId = new String();

	public ReqReplaceUserCurrentMission(){}

	public ReqReplaceUserCurrentMission(int missionId, String userId){
		this.missionId=missionId;
		this.userId = userId;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		missionId = dataInputStream.readInt();
		userId = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(missionId);
		dataOutputStream.writeUTF(userId);
	}

	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}

	public int getMissionId() {
		return missionId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

}