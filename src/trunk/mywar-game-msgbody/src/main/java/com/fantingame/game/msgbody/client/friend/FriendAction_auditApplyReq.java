package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**审核好友申请**/
public class FriendAction_auditApplyReq implements ICodeAble {

		/**审核类型（0拒绝1同意）**/
	private Integer type=0;
	/**用户邮件id**/
	private Integer userMailId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(type);

		outputStream.writeInt(userMailId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		type = inputStream.readInt();

		userMailId = inputStream.readInt();


	}
	
		/**审核类型（0拒绝1同意）**/
    public Integer getType() {
		return type;
	}
	/**审核类型（0拒绝1同意）**/
    public void setType(Integer type) {
		this.type = type;
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
