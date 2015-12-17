package com.fantingame.game.msgbody.server.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**服务器间通讯用户登出**/
public class UserAction_logoutReq implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**用户ip**/
	private String userIp="";
	/**用户在线时长(单位秒)**/
	private Integer onLineSeconds=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userIp);

		outputStream.writeInt(onLineSeconds);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userIp = inputStream.readUTF();

		onLineSeconds = inputStream.readInt();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户ip**/
    public String getUserIp() {
		return userIp;
	}
	/**用户ip**/
    public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	/**用户在线时长(单位秒)**/
    public Integer getOnLineSeconds() {
		return onLineSeconds;
	}
	/**用户在线时长(单位秒)**/
    public void setOnLineSeconds(Integer onLineSeconds) {
		this.onLineSeconds = onLineSeconds;
	}

	
	
}
