package com.adminTool.msgbody;

import java.io.IOException;
import java.util.Date;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

public class UserBuildingSomeInfo implements ICodeAble {
	
	/** 玩家建筑编号 */
	private long userBuildingId;
	
	/** 玩家编号 */
	private long userId;
	
	/** 建筑编号 */
	private int buildingId;
	
	/** 建筑名称 */
	private String buildingName;
	
	/** 建筑等级 */
	private int level;

	/** 第一格npc编号 */
	private int npc1Id;
	
	/** npc是否能生产 */
	private int canNpcOutput;
	
	/** 结束时间 */
	private Date npc1EndTime;
	
	private Date npc1WorkTime;
	
	private int npc1OutputCount;
	
	/**增产者玩家编号**/
	private String increaseUserId1 = new String();
	
	/**加速着玩家编号**/
	private String speedUpUserId1 = new String();
	
	/** 建筑状态 (1正常2冷却中)*/
	private int state;
	
	/** 开始时间 */
	private Date startTime;
	
	/** 结束时间 */
	private Date endTime;

	/** 加速次数 */
	private int accelerateNPCCount;

	/** 第一次加速时间 */
	private Date accelerateaFirstTime;
	
	/**上次加速此建筑的玩家id**/
	private String lastSpeedUpUserId = new String();
	
	public void decode(IXInputStream dataInputStream) throws IOException {
		userBuildingId = Long.valueOf(dataInputStream.readUTF());
		userId = Long.valueOf(dataInputStream.readUTF());
		buildingId = dataInputStream.readInt();
		buildingName = dataInputStream.readUTF();
		level = dataInputStream.readInt();
		npc1Id = dataInputStream.readInt();
		canNpcOutput = dataInputStream.readInt();
		npc1EndTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		npc1WorkTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		npc1OutputCount = dataInputStream.readInt();
		increaseUserId1 = dataInputStream.readUTF();
		speedUpUserId1 = dataInputStream.readUTF();
		state = dataInputStream.readInt();
		startTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		endTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		accelerateNPCCount = dataInputStream.readInt();
		accelerateaFirstTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		lastSpeedUpUserId = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		
		dataOutputStream.writeUTF(userBuildingId+"");
		dataOutputStream.writeUTF(userId+"");
		dataOutputStream.writeInt(buildingId);
		dataOutputStream.writeUTF(buildingName);
		dataOutputStream.writeInt(level);
		dataOutputStream.writeInt(npc1Id);
		dataOutputStream.writeInt(canNpcOutput);
		if(npc1EndTime != null) {
			dataOutputStream.writeUTF(npc1EndTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("0");
		}
		if(npc1WorkTime != null) {
			dataOutputStream.writeUTF(npc1WorkTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("0");
		}
		dataOutputStream.writeInt(npc1OutputCount);
		dataOutputStream.writeUTF(increaseUserId1==null?"":increaseUserId1);
		dataOutputStream.writeUTF(speedUpUserId1);
		dataOutputStream.writeInt(state);
		if(startTime != null) {
			dataOutputStream.writeUTF(startTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("0");
		}
		if(endTime != null) {
			dataOutputStream.writeUTF(endTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("0");
		}
		dataOutputStream.writeInt(accelerateNPCCount);
		if(accelerateaFirstTime != null) {
			dataOutputStream.writeUTF(accelerateaFirstTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("0");
		}
		dataOutputStream.writeUTF(lastSpeedUpUserId);
	}

	/**
	 * 获取 玩家建筑编号 
	 */
	public long getUserBuildingId() {
		return userBuildingId;
	}

	/**
	 * 设置 玩家建筑编号 
	 */
	public void setUserBuildingId(long userBuildingId) {
		this.userBuildingId = userBuildingId;
	}

	/**
	 * 获取 玩家编号 
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置 玩家编号 
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取 建筑编号 
	 */
	public int getBuildingId() {
		return buildingId;
	}

	/**
	 * 设置 建筑编号 
	 */
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	/**
	 * 获取 建筑等级 
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * 设置 建筑等级 
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * 获取 第一格npc编号 
	 */
	public int getNpc1Id() {
		return npc1Id;
	}

	/**
	 * 设置 第一格npc编号 
	 */
	public void setNpc1Id(int npc1Id) {
		this.npc1Id = npc1Id;
	}

	/**
	 * 获取 npc是否能生产 
	 */
	public int getCanNpcOutput() {
		return canNpcOutput;
	}

	/**
	 * 设置 npc是否能生产 
	 */
	public void setCanNpcOutput(int canNpcOutput) {
		this.canNpcOutput = canNpcOutput;
	}

	/**
	 * 获取 结束时间 
	 */
	public Date getNpc1EndTime() {
		return npc1EndTime;
	}

	/**
	 * 设置 结束时间 
	 */
	public void setNpc1EndTime(Date npc1EndTime) {
		this.npc1EndTime = npc1EndTime;
	}

	/**
	 * 获取 npc1WorkTime 
	 */
	public Date getNpc1WorkTime() {
		return npc1WorkTime;
	}

	/**
	 * 设置 npc1WorkTime 
	 */
	public void setNpc1WorkTime(Date npc1WorkTime) {
		this.npc1WorkTime = npc1WorkTime;
	}

	/**
	 * 获取 npc1OutputCount 
	 */
	public int getNpc1OutputCount() {
		return npc1OutputCount;
	}

	/**
	 * 设置 npc1OutputCount 
	 */
	public void setNpc1OutputCount(int npc1OutputCount) {
		this.npc1OutputCount = npc1OutputCount;
	}

	/**
	 * 获取 增产者玩家编号 
	 */
	public String getIncreaseUserId1() {
		return increaseUserId1;
	}

	/**
	 * 设置 增产者玩家编号 
	 */
	public void setIncreaseUserId1(String increaseUserId1) {
		this.increaseUserId1 = increaseUserId1;
	}

	/**
	 * 获取 加速着玩家编号 
	 */
	public String getSpeedUpUserId1() {
		return speedUpUserId1;
	}

	/**
	 * 设置 加速着玩家编号 
	 */
	public void setSpeedUpUserId1(String speedUpUserId1) {
		this.speedUpUserId1 = speedUpUserId1;
	}

	/**
	 * 获取 建筑状态(1正常2冷却中) 
	 */
	public int getState() {
		return state;
	}

	/**
	 * 设置 建筑状态(1正常2冷却中) 
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * 获取 开始时间 
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置 开始时间 
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取 结束时间 
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置 结束时间 
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取 加速次数 
	 */
	public int getAccelerateNPCCount() {
		return accelerateNPCCount;
	}

	/**
	 * 设置 加速次数 
	 */
	public void setAccelerateNPCCount(int accelerateNPCCount) {
		this.accelerateNPCCount = accelerateNPCCount;
	}

	/**
	 * 获取 第一次加速时间 
	 */
	public Date getAccelerateaFirstTime() {
		return accelerateaFirstTime;
	}

	/**
	 * 设置 第一次加速时间 
	 */
	public void setAccelerateaFirstTime(Date accelerateaFirstTime) {
		this.accelerateaFirstTime = accelerateaFirstTime;
	}

	/**
	 * 获取 上次加速此建筑的玩家id 
	 */
	public String getLastSpeedUpUserId() {
		return lastSpeedUpUserId;
	}

	/**
	 * 设置 上次加速此建筑的玩家id 
	 */
	public void setLastSpeedUpUserId(String lastSpeedUpUserId) {
		this.lastSpeedUpUserId = lastSpeedUpUserId;
	}

	/**
	 * 获取 建筑名称 
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * 设置 建筑名称 
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
}
