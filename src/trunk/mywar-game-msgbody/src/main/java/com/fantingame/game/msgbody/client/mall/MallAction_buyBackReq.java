package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**回购**/
public class MallAction_buyBackReq implements ICodeAble {

		/**回购id**/
	private String buyBackId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(buyBackId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		buyBackId = inputStream.readUTF();


	}
	
		/**回购id**/
    public String getBuyBackId() {
		return buyBackId;
	}
	/**回购id**/
    public void setBuyBackId(String buyBackId) {
		this.buyBackId = buyBackId;
	}

	
	
}
