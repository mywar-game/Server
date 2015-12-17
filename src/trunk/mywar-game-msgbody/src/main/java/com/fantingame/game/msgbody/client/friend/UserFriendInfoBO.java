package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户好友信息对象**/
public class UserFriendInfoBO implements ICodeAble {

		/**是否在线（0离线1在线）**/
	private Integer isOnline=0;
	/**好友用户id**/
	private String userId="";
	/**好友名称**/
	private String name="";
	/**阵营**/
	private Integer camp=0;
	/**等级**/
	private Integer level=0;
	/**战斗力**/
	private Integer effective=0;
	/**好友队长的英雄id**/
	private Integer systemHeroId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(isOnline);

		outputStream.writeUTF(userId);

		outputStream.writeUTF(name);

		outputStream.writeInt(camp);

		outputStream.writeInt(level);

		outputStream.writeInt(effective);

		outputStream.writeInt(systemHeroId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		isOnline = inputStream.readInt();

		userId = inputStream.readUTF();

		name = inputStream.readUTF();

		camp = inputStream.readInt();

		level = inputStream.readInt();

		effective = inputStream.readInt();

		systemHeroId = inputStream.readInt();


	}
	
		/**是否在线（0离线1在线）**/
    public Integer getIsOnline() {
		return isOnline;
	}
	/**是否在线（0离线1在线）**/
    public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	/**好友用户id**/
    public String getUserId() {
		return userId;
	}
	/**好友用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**好友名称**/
    public String getName() {
		return name;
	}
	/**好友名称**/
    public void setName(String name) {
		this.name = name;
	}
	/**阵营**/
    public Integer getCamp() {
		return camp;
	}
	/**阵营**/
    public void setCamp(Integer camp) {
		this.camp = camp;
	}
	/**等级**/
    public Integer getLevel() {
		return level;
	}
	/**等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**战斗力**/
    public Integer getEffective() {
		return effective;
	}
	/**战斗力**/
    public void setEffective(Integer effective) {
		this.effective = effective;
	}
	/**好友队长的英雄id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**好友队长的英雄id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	
	
}
