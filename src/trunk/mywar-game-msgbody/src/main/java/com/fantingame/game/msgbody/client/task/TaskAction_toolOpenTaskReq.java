package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**道具开启任务**/
public class TaskAction_toolOpenTaskReq implements ICodeAble {

		/**道具id**/
	private Integer toolId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(toolId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toolId = inputStream.readInt();


	}
	
		/**道具id**/
    public Integer getToolId() {
		return toolId;
	}
	/**道具id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	
	
}
