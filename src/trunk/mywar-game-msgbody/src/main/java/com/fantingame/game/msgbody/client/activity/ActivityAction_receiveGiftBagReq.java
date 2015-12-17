package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取声望奖励**/
public class ActivityAction_receiveGiftBagReq implements ICodeAble {

		/**礼包码**/
	private String code="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(code);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		code = inputStream.readUTF();


	}
	
		/**礼包码**/
    public String getCode() {
		return code;
	}
	/**礼包码**/
    public void setCode(String code) {
		this.code = code;
	}

	
	
}
