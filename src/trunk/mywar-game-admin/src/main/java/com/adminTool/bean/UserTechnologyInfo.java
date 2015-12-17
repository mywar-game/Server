package com.adminTool.bean;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 玩家科技信息  */
public class UserTechnologyInfo implements ICodeAble {

	/**  用户科技编号 **/
	private String userTechnologyId= new String() ;
	/**  用户编号 **/
	private String userId= new String() ;
	/**  科技等级 **/
	private int level;
	/**  科技编号 **/
	private int technologyId;
	/**  科技名称 **/
	private String technologyName= new String() ;
	/**  科技状态 **/
	private int state;
	/**  冷却开始时间 **/
	private String startTime= new String() ;
	/**  冷却结束时间 **/
	private String endTime= new String() ;

	public UserTechnologyInfo(){}

	public UserTechnologyInfo(String userTechnologyId , String userId , int level , int technologyId , String technologyName , int state , String startTime , String endTime){
		this.userTechnologyId=userTechnologyId;
		this.userId=userId;
		this.level=level;
		this.technologyId=technologyId;
		this.technologyName=technologyName;
		this.state=state;
		this.startTime=startTime;
		this.endTime=endTime;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userTechnologyId = dataInputStream.readUTF();
		userId = dataInputStream.readUTF();
		level = dataInputStream.readInt();
		technologyId = dataInputStream.readInt();
		technologyName = dataInputStream.readUTF();
		state = dataInputStream.readInt();
		startTime = dataInputStream.readUTF();
		endTime = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userTechnologyId);
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeInt(level);
		dataOutputStream.writeInt(technologyId);
		dataOutputStream.writeUTF(technologyName);
		dataOutputStream.writeInt(state);
		dataOutputStream.writeUTF(startTime);
		dataOutputStream.writeUTF(endTime);
	}

	public void setUserTechnologyId(String userTechnologyId) {
		this.userTechnologyId = userTechnologyId;
	}

	public String getUserTechnologyId() {
		return userTechnologyId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setTechnologyId(int technologyId) {
		this.technologyId = technologyId;
	}

	public int getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

}