package com.fantingame.game.msgbody.client.pack;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户背包扩展对象**/
public class UserPackExtendBO implements ICodeAble {

		/**道具id**/
	private Integer toolId=0;
	/**位置(1,2,3,4)**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(toolId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toolId = inputStream.readInt();

		pos = inputStream.readInt();


	}
	
		/**道具id**/
    public Integer getToolId() {
		return toolId;
	}
	/**道具id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	/**位置(1,2,3,4)**/
    public Integer getPos() {
		return pos;
	}
	/**位置(1,2,3,4)**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
