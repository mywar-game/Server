package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**审核申请（同意/拒绝）**/
public class LegionAction_auditingApplyReq implements ICodeAble {

		/**审核的用户id**/
	private String auditingUserId="";
	/**1同意2拒绝**/
	private Integer type=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(auditingUserId);

		outputStream.writeInt(type);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		auditingUserId = inputStream.readUTF();

		type = inputStream.readInt();


	}
	
		/**审核的用户id**/
    public String getAuditingUserId() {
		return auditingUserId;
	}
	/**审核的用户id**/
    public void setAuditingUserId(String auditingUserId) {
		this.auditingUserId = auditingUserId;
	}
	/**1同意2拒绝**/
    public Integer getType() {
		return type;
	}
	/**1同意2拒绝**/
    public void setType(Integer type) {
		this.type = type;
	}

	
	
}
