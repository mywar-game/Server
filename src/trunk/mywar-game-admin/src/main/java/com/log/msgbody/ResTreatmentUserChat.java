package com.log.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* ChatTreatmentTask处理玩家聊天，封号信息,cmdCode = 6205  */
public class ResTreatmentUserChat implements ICodeAble {

	/** 被操作的玩家id */
	private String userId;
	/**  操作类型(1.新手2.正常3.禁言4.封号) **/
	private int type;
	/**  禁言结束时间 **/
	private String stateFinishTime = new String();

	public ResTreatmentUserChat() {
	}

	public ResTreatmentUserChat(String userId, int type , String stateFinishTime) {
		this.userId = userId;
		this.type = type;
		this.stateFinishTime = stateFinishTime;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		type = dataInputStream.readInt();
		stateFinishTime = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeInt(type);
		dataOutputStream.writeUTF(stateFinishTime);
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setStateFinishTime(String stateFinishTime) {
		this.stateFinishTime = stateFinishTime;
	}

	public String getStateFinishTime() {
		return stateFinishTime;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

}