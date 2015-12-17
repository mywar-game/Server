package com.fantingame.game.msgbody.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**角色登录接口**/
public class UAction_loginReq implements ICodeAble {

		/**用户登录令牌**/
	private String token="";
	/**用户编号**/
	private String userId="";
	/**渠道编号**/
	private String partnerId="";
	/**服务器编号**/
	private String serverId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		   
				outputStream.writeUTF(token);

		outputStream.writeUTF(userId);

		outputStream.writeUTF(partnerId);

		outputStream.writeUTF(serverId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		   
				token = inputStream.readUTF();

		userId = inputStream.readUTF();

		partnerId = inputStream.readUTF();

		serverId = inputStream.readUTF();


	}
	
		/**用户登录令牌**/
    public String getToken() {
		return token;
	}
	/**用户登录令牌**/
    public void setToken(String token) {
		this.token = token;
	}
	/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**渠道编号**/
    public String getPartnerId() {
		return partnerId;
	}
	/**渠道编号**/
    public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	/**服务器编号**/
    public String getServerId() {
		return serverId;
	}
	/**服务器编号**/
    public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	
	
}
