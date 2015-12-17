package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.UserMessageInfoBO;

/**提交留言信息**/
public class LegionAction_commitMessageRes implements ICodeAble {

		/**用户留言信息**/
	private UserMessageInfoBO userMessageInfoBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userMessageInfoBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userMessageInfoBO=new UserMessageInfoBO();    
		userMessageInfoBO.decode(inputStream);


	}
	
		/**用户留言信息**/
    public UserMessageInfoBO getUserMessageInfoBO() {
		return userMessageInfoBO;
	}
	/**用户留言信息**/
    public void setUserMessageInfoBO(UserMessageInfoBO userMessageInfoBO) {
		this.userMessageInfoBO = userMessageInfoBO;
	}

	
	
}
