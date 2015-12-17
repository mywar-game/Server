package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户留言信息对象**/
public class UserMessageInfoBO implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**用户名称**/
	private String userName="";
	/**留言内容**/
	private String content="";
	/**留言时间**/
	private Long time=0l;
	/**系统英雄id**/
	private Integer systemHeroId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userName);

		outputStream.writeUTF(content);

		outputStream.writeLong(time);

		outputStream.writeInt(systemHeroId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userName = inputStream.readUTF();

		content = inputStream.readUTF();

		time = inputStream.readLong();

		systemHeroId = inputStream.readInt();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户名称**/
    public String getUserName() {
		return userName;
	}
	/**用户名称**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**留言内容**/
    public String getContent() {
		return content;
	}
	/**留言内容**/
    public void setContent(String content) {
		this.content = content;
	}
	/**留言时间**/
    public Long getTime() {
		return time;
	}
	/**留言时间**/
    public void setTime(Long time) {
		this.time = time;
	}
	/**系统英雄id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**系统英雄id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	
	
}
