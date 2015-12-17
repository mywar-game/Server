package com.fantingame.game.msgbody.client.achievement;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户成就对象**/
public class UserAchievementBO implements ICodeAble {

		/**成就id**/
	private Integer achievementId=0;
	/**完成次数**/
	private Integer finishTimes=0;
	/**状态0未完成1已完成2已领取**/
	private Integer status=0;
	/**更新时间**/
	private Long time=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(achievementId);

		outputStream.writeInt(finishTimes);

		outputStream.writeInt(status);

		outputStream.writeLong(time);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		achievementId = inputStream.readInt();

		finishTimes = inputStream.readInt();

		status = inputStream.readInt();

		time = inputStream.readLong();


	}
	
		/**成就id**/
    public Integer getAchievementId() {
		return achievementId;
	}
	/**成就id**/
    public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}
	/**完成次数**/
    public Integer getFinishTimes() {
		return finishTimes;
	}
	/**完成次数**/
    public void setFinishTimes(Integer finishTimes) {
		this.finishTimes = finishTimes;
	}
	/**状态0未完成1已完成2已领取**/
    public Integer getStatus() {
		return status;
	}
	/**状态0未完成1已完成2已领取**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**更新时间**/
    public Long getTime() {
		return time;
	}
	/**更新时间**/
    public void setTime(Long time) {
		this.time = time;
	}

	
	
}
