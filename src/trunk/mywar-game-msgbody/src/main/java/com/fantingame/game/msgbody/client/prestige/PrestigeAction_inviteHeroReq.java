package com.fantingame.game.msgbody.client.prestige;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**邀请英雄**/
public class PrestigeAction_inviteHeroReq implements ICodeAble {

		/**英雄系统id**/
	private Integer systemHeroId=0;
	/**英雄名字**/
	private String heroName="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemHeroId);

		outputStream.writeUTF(heroName);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemHeroId = inputStream.readInt();

		heroName = inputStream.readUTF();


	}
	
		/**英雄系统id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**英雄系统id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**英雄名字**/
    public String getHeroName() {
		return heroName;
	}
	/**英雄名字**/
    public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	
	
}
