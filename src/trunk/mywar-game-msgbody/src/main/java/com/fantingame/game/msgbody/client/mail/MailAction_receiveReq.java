package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取邮件附件**/
public class MailAction_receiveReq implements ICodeAble {

		/**用户邮件id**/
	private Integer userMailId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(userMailId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userMailId = inputStream.readInt();


	}
	
		/**用户邮件id**/
    public Integer getUserMailId() {
		return userMailId;
	}
	/**用户邮件id**/
    public void setUserMailId(Integer userMailId) {
		this.userMailId = userMailId;
	}

	
	
}
