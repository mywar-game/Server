package com.fantingame.game.msgbody.client.message;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**发送跑马灯**/
public class MessageAction_sendMsgReq implements ICodeAble {

		/**跑马灯内容**/
	private String content="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(content);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		content = inputStream.readUTF();


	}
	
		/**跑马灯内容**/
    public String getContent() {
		return content;
	}
	/**跑马灯内容**/
    public void setContent(String content) {
		this.content = content;
	}

	
	
}
