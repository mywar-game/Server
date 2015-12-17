package com.fantingame.game.msgbody.client.prestige;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**酒馆英雄对象**/
public class InviteHeroBO implements ICodeAble {

		/**系统英雄id**/
	private Integer systemHeroId=0;
	/**英雄名称**/
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
	
		/**系统英雄id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**系统英雄id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**英雄名称**/
    public String getHeroName() {
		return heroName;
	}
	/**英雄名称**/
    public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	
	
}
