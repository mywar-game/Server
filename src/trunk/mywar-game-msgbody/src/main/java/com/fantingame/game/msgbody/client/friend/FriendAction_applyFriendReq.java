package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**申请添加好友**/
public class FriendAction_applyFriendReq implements ICodeAble {

		/**对方用户id**/
	private String targetUserId="";
	/**用户名**/
	private String name="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(targetUserId);

		outputStream.writeUTF(name);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		targetUserId = inputStream.readUTF();

		name = inputStream.readUTF();


	}
	
		/**对方用户id**/
    public String getTargetUserId() {
		return targetUserId;
	}
	/**对方用户id**/
    public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	/**用户名**/
    public String getName() {
		return name;
	}
	/**用户名**/
    public void setName(String name) {
		this.name = name;
	}

	
	
}
