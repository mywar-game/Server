package com.fantingame.game.msgbody.client.chat;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.chat.UserChatRecordBO;

/**世界聊天**/
public class ChatAction_worldOfChatRes implements ICodeAble {

		/**用户聊天信息**/
	private UserChatRecordBO userChatRecordBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userChatRecordBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userChatRecordBO=new UserChatRecordBO();    
		userChatRecordBO.decode(inputStream);


	}
	
		/**用户聊天信息**/
    public UserChatRecordBO getUserChatRecordBO() {
		return userChatRecordBO;
	}
	/**用户聊天信息**/
    public void setUserChatRecordBO(UserChatRecordBO userChatRecordBO) {
		this.userChatRecordBO = userChatRecordBO;
	}

	
	
}
