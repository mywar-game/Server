package com.hero.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* 后台更新玩家等级  */
public class UpdateHeroLevelMsg implements ICodeAble {

	/**  英雄ID **/
	private String userHeroId= new String() ;
	/**  英雄ID **/
	private String userId= new String() ;
	/**  修改的玩家等级 **/
	private int level;

	public UpdateHeroLevelMsg(){}

	public UpdateHeroLevelMsg(String userHeroId , String userId , int level){
		this.userHeroId=userHeroId;
		this.userId=userId;
		this.level=level;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userHeroId = dataInputStream.readUTF();
		userId = dataInputStream.readUTF();
		level = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userHeroId);
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeInt(level);
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public String getUserHeroId() {
		return userHeroId;
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

}