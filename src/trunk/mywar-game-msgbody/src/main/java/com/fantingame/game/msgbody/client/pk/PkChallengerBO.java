package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**可挑战者对象**/
public class PkChallengerBO implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**可挑战者名称**/
	private String challengerName="";
	/**系统英雄id**/
	private Integer systemHeroId=0;
	/**排名**/
	private Integer rank=0;
	/**总装等**/
	private Integer power=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(challengerName);

		outputStream.writeInt(systemHeroId);

		outputStream.writeInt(rank);

		outputStream.writeInt(power);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		challengerName = inputStream.readUTF();

		systemHeroId = inputStream.readInt();

		rank = inputStream.readInt();

		power = inputStream.readInt();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**可挑战者名称**/
    public String getChallengerName() {
		return challengerName;
	}
	/**可挑战者名称**/
    public void setChallengerName(String challengerName) {
		this.challengerName = challengerName;
	}
	/**系统英雄id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**系统英雄id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**排名**/
    public Integer getRank() {
		return rank;
	}
	/**排名**/
    public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**总装等**/
    public Integer getPower() {
		return power;
	}
	/**总装等**/
    public void setPower(Integer power) {
		this.power = power;
	}

	
	
}
