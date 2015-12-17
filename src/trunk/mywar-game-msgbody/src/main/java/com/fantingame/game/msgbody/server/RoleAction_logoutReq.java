package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**服务器间通讯用户登出**/
public class RoleAction_logoutReq implements ICodeAble {

		/**用户id**/
	private String userId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		   
				outputStream.writeUTF(userId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		   
				userId = inputStream.readUTF();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
