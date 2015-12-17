package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**接受任务**/
public class TaskAction_addTaskReq implements ICodeAble {

		/**系统任务id**/
	private Integer systemTaskId=0;
	/**星数**/
	private Integer star=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemTaskId);

		outputStream.writeInt(star);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemTaskId = inputStream.readInt();

		star = inputStream.readInt();


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

	
	
}
