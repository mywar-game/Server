package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户日常任务对象**/
public class UserDailyTaskInfoBO implements ICodeAble {

		/**系统任务id**/
	private Integer systemTaskId=0;
	/**星数**/
	private Integer star=0;
	/**0可领取1已领取2已完成**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemTaskId);

		outputStream.writeInt(star);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemTaskId = inputStream.readInt();

		star = inputStream.readInt();

		status = inputStream.readInt();


	}
	
		/**系统任务id**/
    public Integer getSystemTaskId() {
		return systemTaskId;
	}
	/**系统任务id**/
    public void setSystemTaskId(Integer systemTaskId) {
		this.systemTaskId = systemTaskId;
	}
	/**星数**/
    public Integer getStar() {
		return star;
	}
	/**星数**/
    public void setStar(Integer star) {
		this.star = star;
	}
	/**0可领取1已领取2已完成**/
    public Integer getStatus() {
		return status;
	}
	/**0可领取1已领取2已完成**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
