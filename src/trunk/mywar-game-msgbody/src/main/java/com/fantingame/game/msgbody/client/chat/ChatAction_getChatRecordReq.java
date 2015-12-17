package com.fantingame.game.msgbody.client.chat;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取聊天记录**/
public class ChatAction_getChatRecordReq implements ICodeAble {

		/**聊天类型（1世界2阵营3军团4私聊）**/
	private Integer type=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(type);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		type = inputStream.readInt();


	}
	
		/**聊天类型（1世界2阵营3军团4私聊）**/
    public Integer getType() {
		return type;
	}
	/**聊天类型（1世界2阵营3军团4私聊）**/
    public void setType(Integer type) {
		this.type = type;
	}

	
	
}
