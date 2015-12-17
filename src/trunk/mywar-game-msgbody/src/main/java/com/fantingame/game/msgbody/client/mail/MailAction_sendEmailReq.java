package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**发送邮件**/
public class MailAction_sendEmailReq implements ICodeAble {

		/**发往用户id**/
	private String toUserId="";
	/**用户名**/
	private String name="";
	/**内容**/
	private String content="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(toUserId);

		outputStream.writeUTF(name);

		outputStream.writeUTF(content);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toUserId = inputStream.readUTF();

		name = inputStream.readUTF();

		content = inputStream.readUTF();


	}
	
		/**发往用户id**/
    public String getToUserId() {
		return toUserId;
	}
	/**发往用户id**/
    public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	/**用户名**/
    public String getName() {
		return name;
	}
	/**用户名**/
    public void setName(String name) {
		this.name = name;
	}
	/**内容**/
    public String getContent() {
		return content;
	}
	/**内容**/
    public void setContent(String content) {
		this.content = content;
	}

	
	
}
