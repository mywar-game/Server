package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**创建角色接口**/
public class UserAction_creatReq implements ICodeAble {

		/**角色id**/
	private Integer roleId=0;
	/**角色名称**/
	private String roleName="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(roleId);

		outputStream.writeUTF(roleName);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		roleId = inputStream.readInt();

		roleName = inputStream.readUTF();


	}
	
		/**角色id**/
    public Integer getRoleId() {
		return roleId;
	}
	/**角色id**/
    public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**角色名称**/
    public String getRoleName() {
		return roleName;
	}
	/**角色名称**/
    public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
}
