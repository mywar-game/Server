package com.fantingame.game.msgbody.server.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**服务器间通讯用户登录**/
public class UserAction_loginReq implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**用户ip**/
	private String userIp="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userIp);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userIp = inputStream.readUTF();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户ip**/
    public String getUserIp() {
		return userIp;
	}
	/**用户ip**/
    public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	
	
}
