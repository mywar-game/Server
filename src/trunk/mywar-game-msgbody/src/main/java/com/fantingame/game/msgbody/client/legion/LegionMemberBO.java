package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**公会成员对象**/
public class LegionMemberBO implements ICodeAble {

		/**成员用户id**/
	private String userId="";
	/**成员名称**/
	private String userName="";
	/**成员等级**/
	private Integer level=0;
	/**0离线1在线**/
	private Integer status=0;
	/**身份1团长2副团长3普通成员**/
	private Integer identity=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userName);

		outputStream.writeInt(level);

		outputStream.writeInt(status);

		outputStream.writeInt(identity);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userName = inputStream.readUTF();

		level = inputStream.readInt();

		status = inputStream.readInt();

		identity = inputStream.readInt();


	}
	
		/**成员用户id**/
    public String getUserId() {
		return userId;
	}
	/**成员用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**成员名称**/
    public String getUserName() {
		return userName;
	}
	/**成员名称**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**成员等级**/
    public Integer getLevel() {
		return level;
	}
	/**成员等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**0离线1在线**/
    public Integer getStatus() {
		return status;
	}
	/**0离线1在线**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**身份1团长2副团长3普通成员**/
    public Integer getIdentity() {
		return identity;
	}
	/**身份1团长2副团长3普通成员**/
    public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	
	
}
