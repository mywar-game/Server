package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**发送跑马灯信息**/
public class AdminAction_sendSystemMsgReq implements ICodeAble {

		/**邮件内容**/
	private String content="";
	/**渠道号**/
	private String partnerId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(content);

		outputStream.writeUTF(partnerId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		content = inputStream.readUTF();

		partnerId = inputStream.readUTF();


	}
	
		/**邮件内容**/
    public String getContent() {
		return content;
	}
	/**邮件内容**/
    public void setContent(String content) {
		this.content = content;
	}
	/**渠道号**/
    public String getPartnerId() {
		return partnerId;
	}
	/**渠道号**/
    public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	
	
}
