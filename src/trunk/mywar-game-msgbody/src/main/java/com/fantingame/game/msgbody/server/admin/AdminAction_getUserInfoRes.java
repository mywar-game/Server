package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.user.UserBO;

/**获取用户信息**/
public class AdminAction_getUserInfoRes implements ICodeAble {

		/**用户信息**/
	private UserBO user=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		user.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		user=new UserBO();    
		user.decode(inputStream);


	}
	
		/**用户信息**/
    public UserBO getUser() {
		return user;
	}
	/**用户信息**/
    public void setUser(UserBO user) {
		this.user = user;
	}

	
	
}
