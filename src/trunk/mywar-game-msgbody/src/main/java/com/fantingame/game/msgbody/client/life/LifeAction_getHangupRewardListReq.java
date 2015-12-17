package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取挂机奖励**/
public class LifeAction_getHangupRewardListReq implements ICodeAble {

		/**的类别（1矿场2花圃3渔场）**/
	private Integer category=0;
	/**用户英雄id**/
	private String userHeroId="";
	/**用户好友id**/
	private String userFriendId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(category);

		outputStream.writeUTF(userHeroId);

		outputStream.writeUTF(userFriendId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		category = inputStream.readInt();

		userHeroId = inputStream.readUTF();

		userFriendId = inputStream.readUTF();


	}
	
		/**的类别（1矿场2花圃3渔场）**/
    public Integer getCategory() {
		return category;
	}
	/**的类别（1矿场2花圃3渔场）**/
    public void setCategory(Integer category) {
		this.category = category;
	}
	/**用户英雄id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**用户英雄id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**用户好友id**/
    public String getUserFriendId() {
		return userFriendId;
	}
	/**用户好友id**/
    public void setUserFriendId(String userFriendId) {
		this.userFriendId = userFriendId;
	}

	
	
}
