package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户申请信息**/
public class UserApplyLegionBO implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**用户名**/
	private String userName="";
	/**等级**/
	private Integer level=0;
	/**战斗力**/
	private Integer effective=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userName);

		outputStream.writeInt(level);

		outputStream.writeInt(effective);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userName = inputStream.readUTF();

		level = inputStream.readInt();

		effective = inputStream.readInt();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户名**/
    public String getUserName() {
		return userName;
	}
	/**用户名**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**等级**/
    public Integer getLevel() {
		return level;
	}
	/**等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**战斗力**/
    public Integer getEffective() {
		return effective;
	}
	/**战斗力**/
    public void setEffective(Integer effective) {
		this.effective = effective;
	}

	
	
}
