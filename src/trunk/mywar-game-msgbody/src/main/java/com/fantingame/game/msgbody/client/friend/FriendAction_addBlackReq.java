package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**添加黑名单**/
public class FriendAction_addBlackReq implements ICodeAble {

		/**对方用户id**/
	private String targetUserId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(targetUserId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		targetUserId = inputStream.readUTF();


	}
	
		/**对方用户id**/
    public String getTargetUserId() {
		return targetUserId;
	}
	/**对方用户id**/
    public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	
	
}
