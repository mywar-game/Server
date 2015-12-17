package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**战斗日志对象**/
public class PkFightLogBO implements ICodeAble {

		/**用户姓名**/
	private String targetUserName="";
	/**胜负（0负1胜）**/
	private Integer flag=0;
	/**系统英雄id**/
	private Integer systemHeroId=0;
	/**等级**/
	private Integer level=0;
	/**挑战时间**/
	private Long fightTime=0l;
	/**自己的排名**/
	private Integer rank=0;
	/**排名变化**/
	private Integer changeRank=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(targetUserName);

		outputStream.writeInt(flag);

		outputStream.writeInt(systemHeroId);

		outputStream.writeInt(level);

		outputStream.writeLong(fightTime);

		outputStream.writeInt(rank);

		outputStream.writeInt(changeRank);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		targetUserName = inputStream.readUTF();

		flag = inputStream.readInt();

		systemHeroId = inputStream.readInt();

		level = inputStream.readInt();

		fightTime = inputStream.readLong();

		rank = inputStream.readInt();

		changeRank = inputStream.readInt();


	}
	
		/**用户姓名**/
    public String getTargetUserName() {
		return targetUserName;
	}
	/**用户姓名**/
    public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}
	/**胜负（0负1胜）**/
    public Integer getFlag() {
		return flag;
	}
	/**胜负（0负1胜）**/
    public void setFlag(Integer flag) {
		this.flag = flag;
	}
	/**系统英雄id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**系统英雄id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**等级**/
    public Integer getLevel() {
		return level;
	}
	/**等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**挑战时间**/
    public Long getFightTime() {
		return fightTime;
	}
	/**挑战时间**/
    public void setFightTime(Long fightTime) {
		this.fightTime = fightTime;
	}
	/**自己的排名**/
    public Integer getRank() {
		return rank;
	}
	/**自己的排名**/
    public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**排名变化**/
    public Integer getChangeRank() {
		return changeRank;
	}
	/**排名变化**/
    public void setChangeRank(Integer changeRank) {
		this.changeRank = changeRank;
	}

	
	
}
