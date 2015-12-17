package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**开除会员**/
public class LegionAction_fireLegionMemberReq implements ICodeAble {

		/**开除的用户id**/
	private String fireUserId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(fireUserId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		fireUserId = inputStream.readUTF();


	}
	
		/**开除的用户id**/
    public String getFireUserId() {
		return fireUserId;
	}
	/**开除的用户id**/
    public void setFireUserId(String fireUserId) {
		this.fireUserId = fireUserId;
	}

	
	
}
