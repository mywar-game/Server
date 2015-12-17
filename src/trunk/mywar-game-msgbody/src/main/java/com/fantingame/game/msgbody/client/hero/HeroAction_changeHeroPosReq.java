package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**英雄上阵**/
public class HeroAction_changeHeroPosReq implements ICodeAble {

		/**用户英雄唯一id**/
	private String userHeroId="";
	/**要上到的位置，如果为0则表示该英雄下阵**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userHeroId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroId = inputStream.readUTF();

		pos = inputStream.readInt();


	}
	
		/**用户英雄唯一id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**用户英雄唯一id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**要上到的位置，如果为0则表示该英雄下阵**/
    public Integer getPos() {
		return pos;
	}
	/**要上到的位置，如果为0则表示该英雄下阵**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
