package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**世界Boss排行榜对象**/
public class UserBossRankBO implements ICodeAble {

		/**排名**/
	private Integer rank=0;
	/**用户名**/
	private String userName="";
	/**值**/
	private Long value=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(rank);

		outputStream.writeUTF(userName);

		outputStream.writeLong(value);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		rank = inputStream.readInt();

		userName = inputStream.readUTF();

		value = inputStream.readLong();


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
	/**值**/
    public Long getValue() {
		return value;
	}
	/**值**/
    public void setValue(Long value) {
		this.value = value;
	}

	
	
}
