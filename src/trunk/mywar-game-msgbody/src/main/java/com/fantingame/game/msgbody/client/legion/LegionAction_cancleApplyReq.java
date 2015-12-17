package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**取消申请**/
public class LegionAction_cancleApplyReq implements ICodeAble {

		/**公会id**/
	private String legionId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(legionId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		legionId = inputStream.readUTF();


	}
	
		/**公会id**/
    public String getLegionId() {
		return legionId;
	}
	/**公会id**/
    public void setLegionId(String legionId) {
		this.legionId = legionId;
	}

	
	
}
