package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**参战好友信息对象**/
public class BattleUserInfoBO implements ICodeAble {

		/**好友用户id**/
	private String userId="";
	/**好友名称**/
	private String name="";
	/**战斗力**/
	private Integer effective=0;
	/**好友队长的英雄id**/
	private Integer systemHeroId=0;
	/**0非好友1好友**/
	private Integer isFriend=0;
	/**等级**/
	private Integer level=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(name);

		outputStream.writeInt(effective);

		outputStream.writeInt(systemHeroId);

		outputStream.writeInt(isFriend);

		outputStream.writeInt(level);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		name = inputStream.readUTF();

		effective = inputStream.readInt();

		systemHeroId = inputStream.readInt();

		isFriend = inputStream.readInt();

		level = inputStream.readInt();


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
	/**0非好友1好友**/
    public Integer getIsFriend() {
		return isFriend;
	}
	/**0非好友1好友**/
    public void setIsFriend(Integer isFriend) {
		this.isFriend = isFriend;
	}
	/**等级**/
    public Integer getLevel() {
		return level;
	}
	/**等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}

	
	
}
