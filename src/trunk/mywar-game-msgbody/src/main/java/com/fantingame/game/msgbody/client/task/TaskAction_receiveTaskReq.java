package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取任务奖励**/
public class TaskAction_receiveTaskReq implements ICodeAble {

		/**系统任务id**/
	private Integer systemTaskId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemTaskId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemTaskId = inputStream.readInt();


	}
	
		/**系统任务id**/
    public Integer getSystemTaskId() {
		return systemTaskId;
	}
	/**系统任务id**/
    public void setSystemTaskId(Integer systemTaskId) {
		this.systemTaskId = systemTaskId;
	}

	
	
}
