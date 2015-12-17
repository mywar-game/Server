package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**用户生活信息对象**/
public class UserLifeInfoBO implements ICodeAble {

		/**类别（1挖矿2花圃3钓鱼）**/
	private Integer category=0;
	/**状态（0未创建1未挂机2正在挂机）**/
	private Integer status=0;
	/**用户编号**/
	private String userId="";
	/**用户英雄唯一id**/
	private String userHeroId1="";
	/**用户英雄唯一id**/
	private String userHeroId2="";
	/**用户英雄唯一id**/
	private String userHeroId3="";
	/**用户好友id**/
	private String userFriendId="";
	/**用户好友名称**/
	private String userFriendName="";
	/**好友队长英雄id**/
	private Integer friendSystemHeroId=0;
	/**剩余时间**/
	private Long remainderTime=0l;
	/**现在的收益**/
	private CommonGoodsBeanBO drop=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(category);

		outputStream.writeInt(status);

		outputStream.writeUTF(userId);

		outputStream.writeUTF(userHeroId1);

		outputStream.writeUTF(userHeroId2);

		outputStream.writeUTF(userHeroId3);

		outputStream.writeUTF(userFriendId);

		outputStream.writeUTF(userFriendName);

		outputStream.writeInt(friendSystemHeroId);

		outputStream.writeLong(remainderTime);

		drop.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		category = inputStream.readInt();

		status = inputStream.readInt();

		userId = inputStream.readUTF();

		userHeroId1 = inputStream.readUTF();

		userHeroId2 = inputStream.readUTF();

		userHeroId3 = inputStream.readUTF();

		userFriendId = inputStream.readUTF();

		userFriendName = inputStream.readUTF();

		friendSystemHeroId = inputStream.readInt();

		remainderTime = inputStream.readLong();

		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);


	}
	
		/**类别（1挖矿2花圃3钓鱼）**/
    public Integer getCategory() {
		return category;
	}
	/**类别（1挖矿2花圃3钓鱼）**/
    public void setCategory(Integer category) {
		this.category = category;
	}
	/**状态（0未创建1未挂机2正在挂机）**/
    public Integer getStatus() {
		return status;
	}
	/**状态（0未创建1未挂机2正在挂机）**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户英雄唯一id**/
    public String getUserHeroId1() {
		return userHeroId1;
	}
	/**用户英雄唯一id**/
    public void setUserHeroId1(String userHeroId1) {
		this.userHeroId1 = userHeroId1;
	}
	/**用户英雄唯一id**/
    public String getUserHeroId2() {
		return userHeroId2;
	}
	/**用户英雄唯一id**/
    public void setUserHeroId2(String userHeroId2) {
		this.userHeroId2 = userHeroId2;
	}
	/**用户英雄唯一id**/
    public String getUserHeroId3() {
		return userHeroId3;
	}
	/**用户英雄唯一id**/
    public void setUserHeroId3(String userHeroId3) {
		this.userHeroId3 = userHeroId3;
	}
	/**用户好友id**/
    public String getUserFriendId() {
		return userFriendId;
	}
	/**用户好友id**/
    public void setUserFriendId(String userFriendId) {
		this.userFriendId = userFriendId;
	}
	/**用户好友名称**/
    public String getUserFriendName() {
		return userFriendName;
	}
	/**用户好友名称**/
    public void setUserFriendName(String userFriendName) {
		this.userFriendName = userFriendName;
	}
	/**好友队长英雄id**/
    public Integer getFriendSystemHeroId() {
		return friendSystemHeroId;
	}
	/**好友队长英雄id**/
    public void setFriendSystemHeroId(Integer friendSystemHeroId) {
		this.friendSystemHeroId = friendSystemHeroId;
	}
	/**剩余时间**/
    public Long getRemainderTime() {
		return remainderTime;
	}
	/**剩余时间**/
    public void setRemainderTime(Long remainderTime) {
		this.remainderTime = remainderTime;
	}
	/**现在的收益**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**现在的收益**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}

	
	
}
