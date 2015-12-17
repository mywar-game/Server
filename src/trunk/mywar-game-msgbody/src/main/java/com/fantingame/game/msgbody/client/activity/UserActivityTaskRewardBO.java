package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户活跃度奖励对象**/
public class UserActivityTaskRewardBO implements ICodeAble {

		/**奖励id**/
	private Integer activityTaskRewardId=0;
	/**0不能领取1未领取2已领取**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(activityTaskRewardId);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		activityTaskRewardId = inputStream.readInt();

		status = inputStream.readInt();


	}
	
		/**奖励id**/
    public Integer getActivityTaskRewardId() {
		return activityTaskRewardId;
	}
	/**奖励id**/
    public void setActivityTaskRewardId(Integer activityTaskRewardId) {
		this.activityTaskRewardId = activityTaskRewardId;
	}
	/**0不能领取1未领取2已领取**/
    public Integer getStatus() {
		return status;
	}
	/**0不能领取1未领取2已领取**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
