package com.fantingame.game.msgbody.client.achievement;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取用户成就奖励**/
public class AchievementAction_receiveAchievementRewardReq implements ICodeAble {

		/**成就id**/
	private Integer achievementId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(achievementId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		achievementId = inputStream.readInt();


	}
	
		/**成就id**/
    public Integer getAchievementId() {
		return achievementId;
	}
	/**成就id**/
    public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}

	
	
}
