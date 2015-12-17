package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**开始竞技场战斗**/
public class PkAction_startPkFightReq implements ICodeAble {

		/**挑战的用户id**/
	private String targetUserId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(targetUserId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		targetUserId = inputStream.readUTF();


	}
	
		/**挑战的用户id**/
    public String getTargetUserId() {
		return targetUserId;
	}
	/**挑战的用户id**/
    public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	
	
}
