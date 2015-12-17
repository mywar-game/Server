package com.adminTool.bean;

import java.io.IOException;
import java.util.Date;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

public class UserTreasureQueue implements ICodeAble {

	/** 道具队列编号 */
	private long userTreasureQueueId;
	
	/** 用户编号 */
	private long userId;
	
	/** 道具编号 */
	private int treasureId;
	
	/** 目标编号*/
	private long targetId;
	
	/** 道具使用类型(1对单个英雄，2对所有英雄 ,3对玩家) */
	private int type;
	
	/** 开始时间 */
	private Date startTime;
	
	/** 结束时间 */
	private Date endTime;

	public void decode(IXInputStream dataInputStream) throws IOException {
		userTreasureQueueId = Long.valueOf(dataInputStream.readUTF());
		userId = Long.valueOf(dataInputStream.readUTF());
		treasureId = dataInputStream.readInt();
		targetId = Long.valueOf(dataInputStream.readUTF());
		type = dataInputStream.readInt();
		startTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		endTime = new Date(Long.valueOf(dataInputStream.readUTF()));
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userTreasureQueueId+"");
		dataOutputStream.writeUTF(userId+"");
		dataOutputStream.writeInt(treasureId);
		dataOutputStream.writeUTF(targetId+"");
		dataOutputStream.writeInt(type);
		if (startTime == null) {
			dataOutputStream.writeUTF("");
		} else {
			dataOutputStream.writeUTF(startTime.getTime() + "");
		}
		if (endTime == null) {
			dataOutputStream.writeUTF("");
		} else {
			dataOutputStream.writeUTF(endTime.getTime() + "");
		}
		
	}

	public long getUserTreasureQueueId() {
		return userTreasureQueueId;
	}

	public void setUserTreasureQueueId(long userTreasureQueueId) {
		this.userTreasureQueueId = userTreasureQueueId;
	}

	public int getTreasureId() {
		return treasureId;
	}

	public void setTreasureId(int treasureId) {
		this.treasureId = treasureId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
