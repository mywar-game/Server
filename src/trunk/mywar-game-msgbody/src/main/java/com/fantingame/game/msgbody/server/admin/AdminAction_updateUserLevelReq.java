package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**修改用户等级**/
public class AdminAction_updateUserLevelReq implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**用户等级**/
	private Integer targetLevel=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(targetLevel);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		targetLevel = inputStream.readInt();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户等级**/
    public Integer getTargetLevel() {
		return targetLevel;
	}
	/**用户等级**/
    public void setTargetLevel(Integer targetLevel) {
		this.targetLevel = targetLevel;
	}

	
	
}
