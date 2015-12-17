package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**提交任务(有些需要客户端提交的任务，如-寻找npc)**/
public class TaskAction_commitTaskReq implements ICodeAble {

		/**系统任务id**/
	private Integer systemTaskId=0;
	/**完成次数**/
	private Integer times=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemTaskId);

		outputStream.writeInt(times);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemTaskId = inputStream.readInt();

		times = inputStream.readInt();


	}
	
		/**系统任务id**/
    public Integer getSystemTaskId() {
		return systemTaskId;
	}
	/**系统任务id**/
    public void setSystemTaskId(Integer systemTaskId) {
		this.systemTaskId = systemTaskId;
	}
	/**完成次数**/
    public Integer getTimes() {
		return times;
	}
	/**完成次数**/
    public void setTimes(Integer times) {
		this.times = times;
	}

	
	
}
