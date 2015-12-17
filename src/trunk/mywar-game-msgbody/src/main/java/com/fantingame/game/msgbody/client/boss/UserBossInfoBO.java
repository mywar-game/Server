package com.fantingame.game.msgbody.client.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户boss战的相关信息**/
public class UserBossInfoBO implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**用户的状态0死亡1活着2已复活**/
	private Integer status=0;
	/**用户的死亡时间（时间戳）**/
	private Long dieTime=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(status);

		outputStream.writeLong(dieTime);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		status = inputStream.readInt();

		dieTime = inputStream.readLong();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户的状态0死亡1活着2已复活**/
    public Integer getStatus() {
		return status;
	}
	/**用户的状态0死亡1活着2已复活**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**用户的死亡时间（时间戳）**/
    public Long getDieTime() {
		return dieTime;
	}
	/**用户的死亡时间（时间戳）**/
    public void setDieTime(Long dieTime) {
		this.dieTime = dieTime;
	}

	
	
}
