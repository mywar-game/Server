package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户职业信息对象**/
public class UserActivityTaskBO implements ICodeAble {

		/**活跃度id**/
	private Integer activityTaskId=0;
	/**已完成的次数**/
	private Integer finishTimes=0;
	/**0未完成1完成**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(activityTaskId);

		outputStream.writeInt(finishTimes);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		activityTaskId = inputStream.readInt();

		finishTimes = inputStream.readInt();

		status = inputStream.readInt();


	}
	
		/**活跃度id**/
    public Integer getActivityTaskId() {
		return activityTaskId;
	}
	/**活跃度id**/
    public void setActivityTaskId(Integer activityTaskId) {
		this.activityTaskId = activityTaskId;
	}
	/**已完成的次数**/
    public Integer getFinishTimes() {
		return finishTimes;
	}
	/**已完成的次数**/
    public void setFinishTimes(Integer finishTimes) {
		this.finishTimes = finishTimes;
	}
	/**0未完成1完成**/
    public Integer getStatus() {
		return status;
	}
	/**0未完成1完成**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
