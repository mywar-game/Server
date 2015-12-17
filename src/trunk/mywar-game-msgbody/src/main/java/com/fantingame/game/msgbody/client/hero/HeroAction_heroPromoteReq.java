package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**英雄进阶**/
public class HeroAction_heroPromoteReq implements ICodeAble {

		/**用户英雄id**/
	private String userHeroId="";
	/**进阶后的系统英雄id**/
	private Integer proSystemHeroId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userHeroId);

		outputStream.writeInt(proSystemHeroId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroId = inputStream.readUTF();

		proSystemHeroId = inputStream.readInt();


	}
	
		/**用户英雄id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**用户英雄id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**进阶后的系统英雄id**/
    public Integer getProSystemHeroId() {
		return proSystemHeroId;
	}
	/**进阶后的系统英雄id**/
    public void setProSystemHeroId(Integer proSystemHeroId) {
		this.proSystemHeroId = proSystemHeroId;
	}

	
	
}
