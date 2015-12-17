package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**推送房主信息**/
public class Boss_pushBossRoomOwnerNotify implements ICodeAble {

		/**房主的用户id**/
	private String userId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();


	}
	
		/**房主的用户id**/
    public String getUserId() {
		return userId;
	}
	/**房主的用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
