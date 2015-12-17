package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**删除黑名单**/
public class FriendAction_deleteBlackReq implements ICodeAble {

		/**好友id**/
	private String userBlackId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userBlackId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userBlackId = inputStream.readUTF();


	}
	
		/**好友id**/
    public String getUserBlackId() {
		return userBlackId;
	}
	/**好友id**/
    public void setUserBlackId(String userBlackId) {
		this.userBlackId = userBlackId;
	}

	
	
}
