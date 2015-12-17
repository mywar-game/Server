package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**记录打点日志**/
public class UserAction_recordActionLogReq implements ICodeAble {

		/**打点id**/
	private Integer actionId=0;
	/**ip**/
	private String ip="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(actionId);

		outputStream.writeUTF(ip);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		actionId = inputStream.readInt();

		ip = inputStream.readUTF();


	}
	
		/**打点id**/
    public Integer getActionId() {
		return actionId;
	}
	/**打点id**/
    public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	/**ip**/
    public String getIp() {
		return ip;
	}
	/**ip**/
    public void setIp(String ip) {
		this.ip = ip;
	}

	
	
}
