package com.fantingame.game.msgbody.client.tool;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**打开宝箱**/
public class ToolAction_openBoxReq implements ICodeAble {

		/**宝箱id**/
	private Integer toolId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(toolId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toolId = inputStream.readInt();


	}
	
		/**宝箱id**/
    public Integer getToolId() {
		return toolId;
	}
	/**宝箱id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	
	
}
