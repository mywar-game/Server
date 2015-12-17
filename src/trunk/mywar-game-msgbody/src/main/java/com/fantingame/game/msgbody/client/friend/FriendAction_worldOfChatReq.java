package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**世界聊天**/
public class FriendAction_worldOfChatReq implements ICodeAble {

		/**聊天内容**/
	private String content="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(content);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		content = inputStream.readUTF();


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
