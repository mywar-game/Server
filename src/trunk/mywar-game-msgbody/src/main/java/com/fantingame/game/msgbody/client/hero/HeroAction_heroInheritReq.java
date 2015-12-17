package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**英雄传承**/
public class HeroAction_heroInheritReq implements ICodeAble {

		/**传承者**/
	private String userHeroId="";
	/**承受者**/
	private String targetUserHeroId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userHeroId);

		outputStream.writeUTF(targetUserHeroId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroId = inputStream.readUTF();

		targetUserHeroId = inputStream.readUTF();


	}
	
		/**传承者**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**传承者**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**承受者**/
    public String getTargetUserHeroId() {
		return targetUserHeroId;
	}
	/**承受者**/
    public void setTargetUserHeroId(String targetUserHeroId) {
		this.targetUserHeroId = targetUserHeroId;
	}

	
	
}
