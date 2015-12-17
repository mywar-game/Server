package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**审核申请（同意/拒绝）**/
public class LegionAction_auditingApplyRes implements ICodeAble {

		/**审核的用户id**/
	private String auditingUserId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(auditingUserId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		auditingUserId = inputStream.readUTF();


	}
	
		/**审核的用户id**/
    public String getAuditingUserId() {
		return auditingUserId;
	}
	/**审核的用户id**/
    public void setAuditingUserId(String auditingUserId) {
		this.auditingUserId = auditingUserId;
	}

	
	
}
