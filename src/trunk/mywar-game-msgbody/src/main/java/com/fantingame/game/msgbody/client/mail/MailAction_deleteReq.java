package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**删除邮件**/
public class MailAction_deleteReq implements ICodeAble {

		/**用户邮件ids（1,2,3）**/
	private String userMailIds="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userMailIds);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userMailIds = inputStream.readUTF();


	}
	
		/**用户邮件ids（1,2,3）**/
    public String getUserMailIds() {
		return userMailIds;
	}
	/**用户邮件ids（1,2,3）**/
    public void setUserMailIds(String userMailIds) {
		this.userMailIds = userMailIds;
	}

	
	
}
