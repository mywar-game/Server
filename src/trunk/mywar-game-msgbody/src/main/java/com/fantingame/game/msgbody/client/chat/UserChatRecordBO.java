package com.fantingame.game.msgbody.client.chat;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户聊天内容对象**/
public class UserChatRecordBO implements ICodeAble {

		/**发言的用户id**/
	private String userId="";
	/**发言用户名**/
	private String userName="";
	/**目标用户id**/
	private String targetUserId="";
	/**目标用户名**/
	private String targetUserName="";
	/**聊天内容**/
	private String content="";
	/**聊天类型(1世界2阵营3军团4私聊)**/
	private Integer type=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userName);

		outputStream.writeUTF(targetUserId);

		outputStream.writeUTF(targetUserName);

		outputStream.writeUTF(content);

		outputStream.writeInt(type);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userName = inputStream.readUTF();

		targetUserId = inputStream.readUTF();

		targetUserName = inputStream.readUTF();

		content = inputStream.readUTF();

		type = inputStream.readInt();


	}
	
		/**发言的用户id**/
    public String getUserId() {
		return userId;
	}
	/**发言的用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**发言用户名**/
    public String getUserName() {
		return userName;
	}
	/**发言用户名**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**目标用户id**/
    public String getTargetUserId() {
		return targetUserId;
	}
	/**目标用户id**/
    public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	/**目标用户名**/
    public String getTargetUserName() {
		return targetUserName;
	}
	/**目标用户名**/
    public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}
	/**聊天内容**/
    public String getContent() {
		return content;
	}
	/**聊天内容**/
    public void setContent(String content) {
		this.content = content;
	}
	/**聊天类型(1世界2阵营3军团4私聊)**/
    public Integer getType() {
		return type;
	}
	/**聊天类型(1世界2阵营3军团4私聊)**/
    public void setType(Integer type) {
		this.type = type;
	}

	
	
}
