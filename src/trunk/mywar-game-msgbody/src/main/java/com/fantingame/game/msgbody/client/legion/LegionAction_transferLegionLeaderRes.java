package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**转让军团长**/
public class LegionAction_transferLegionLeaderRes implements ICodeAble {

		/**转成会长用户id**/
	private String beLeaderUserId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(beLeaderUserId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		beLeaderUserId = inputStream.readUTF();


	}
	
		/**转成会长用户id**/
    public String getBeLeaderUserId() {
		return beLeaderUserId;
	}
	/**转成会长用户id**/
    public void setBeLeaderUserId(String beLeaderUserId) {
		this.beLeaderUserId = beLeaderUserId;
	}

	
	
}
