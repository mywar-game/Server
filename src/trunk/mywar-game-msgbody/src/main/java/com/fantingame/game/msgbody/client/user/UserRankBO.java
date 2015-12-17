package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户排行榜对象**/
public class UserRankBO implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**排名**/
	private Integer rank=0;
	/**用户名**/
	private String userName="";
	/**队长头像**/
	private Integer systemHeroId=0;
	/**排行榜的值**/
	private Long value=0l;
	/**军团名称**/
	private String leginName="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(rank);

		outputStream.writeUTF(userName);

		outputStream.writeInt(systemHeroId);

		outputStream.writeLong(value);

		outputStream.writeUTF(leginName);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		rank = inputStream.readInt();

		userName = inputStream.readUTF();

		systemHeroId = inputStream.readInt();

		value = inputStream.readLong();

		leginName = inputStream.readUTF();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**排名**/
    public Integer getRank() {
		return rank;
	}
	/**排名**/
    public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**用户名**/
    public String getUserName() {
		return userName;
	}
	/**用户名**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**队长头像**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**队长头像**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**排行榜的值**/
    public Long getValue() {
		return value;
	}
	/**排行榜的值**/
    public void setValue(Long value) {
		this.value = value;
	}
	/**军团名称**/
    public String getLeginName() {
		return leginName;
	}
	/**军团名称**/
    public void setLeginName(String leginName) {
		this.leginName = leginName;
	}

	
	
}
