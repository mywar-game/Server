package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户竞技场信息对象**/
public class UserPkInfoBO implements ICodeAble {

		/**排名**/
	private Integer rank=0;
	/**剩余挑战次数**/
	private Integer challengeTimes=0;
	/**剩余可挑战时间**/
	private Long remainderTime=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(rank);

		outputStream.writeInt(challengeTimes);

		outputStream.writeLong(remainderTime);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		rank = inputStream.readInt();

		challengeTimes = inputStream.readInt();

		remainderTime = inputStream.readLong();


	}
	
		/**排名**/
    public Integer getRank() {
		return rank;
	}
	/**排名**/
    public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**剩余挑战次数**/
    public Integer getChallengeTimes() {
		return challengeTimes;
	}
	/**剩余挑战次数**/
    public void setChallengeTimes(Integer challengeTimes) {
		this.challengeTimes = challengeTimes;
	}
	/**剩余可挑战时间**/
    public Long getRemainderTime() {
		return remainderTime;
	}
	/**剩余可挑战时间**/
    public void setRemainderTime(Long remainderTime) {
		this.remainderTime = remainderTime;
	}

	
	
}
