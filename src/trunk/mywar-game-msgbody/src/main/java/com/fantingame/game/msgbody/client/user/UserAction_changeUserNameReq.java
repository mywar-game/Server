package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**更改昵称**/
public class UserAction_changeUserNameReq implements ICodeAble {

		/**更改的昵称**/
	private String name="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(name);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		name = inputStream.readUTF();


	}
	
		/**更改的昵称**/
    public String getName() {
		return name;
	}
	/**更改的昵称**/
    public void setName(String name) {
		this.name = name;
	}

	
	
}
