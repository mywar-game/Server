package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取邮件列表**/
public class MailAction_getMailListReq implements ICodeAble {

		/**最大的邮件id**/
	private Integer mailId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mailId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mailId = inputStream.readInt();


	}
	
		/**最大的邮件id**/
    public Integer getMailId() {
		return mailId;
	}
	/**最大的邮件id**/
    public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	
	
}
