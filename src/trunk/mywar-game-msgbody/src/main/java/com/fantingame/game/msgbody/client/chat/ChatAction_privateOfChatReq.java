package com.fantingame.game.msgbody.client.chat;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**好友私聊**/
public class ChatAction_privateOfChatReq implements ICodeAble {

		/**用户名**/
	private String userName="";
	/**私聊的用户id**/
	private String toUserId="";
	/**聊天内容**/
	private String content="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userName);

		outputStream.writeUTF(toUserId);

		outputStream.writeUTF(content);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userName = inputStream.readUTF();

		toUserId = inputStream.readUTF();

		content = inputStream.readUTF();


	}
	
		/**用户名**/
    public String getUserName() {
		return userName;
	}
	/**用户名**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**私聊的用户id**/
    public String getToUserId() {
		return toUserId;
	}
	/**私聊的用户id**/
    public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	/**聊天内容**/
    public String getContent() {
		return content;
	}
	/**聊天内容**/
    public void setContent(String content) {
		this.content = content;
	}

	
	
}
