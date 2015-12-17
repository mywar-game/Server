package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**给用户发钻石**/
public class AdminAction_addUserMoneyRes implements ICodeAble {

		/**返回的结果**/
	private String result="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(result);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		result = inputStream.readUTF();


	}
	
		/**返回的结果**/
    public String getResult() {
		return result;
	}
	/**返回的结果**/
    public void setResult(String result) {
		this.result = result;
	}

	
	
}
