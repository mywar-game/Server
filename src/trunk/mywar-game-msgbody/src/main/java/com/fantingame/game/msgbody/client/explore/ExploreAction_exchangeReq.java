package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**兑换英雄**/
public class ExploreAction_exchangeReq implements ICodeAble {

		/**英雄系统id**/
	private Integer systemHeroId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemHeroId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemHeroId = inputStream.readInt();


	}
	
		/**英雄系统id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**英雄系统id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	
	
}
