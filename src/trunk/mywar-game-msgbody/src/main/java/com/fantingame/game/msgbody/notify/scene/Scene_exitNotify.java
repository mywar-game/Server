package com.fantingame.game.msgbody.notify.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户退出场景**/
public class Scene_exitNotify implements ICodeAble {

		/**退出的用户id**/
	private String userId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();


	}
	
		/**退出的用户id**/
    public String getUserId() {
		return userId;
	}
	/**退出的用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
