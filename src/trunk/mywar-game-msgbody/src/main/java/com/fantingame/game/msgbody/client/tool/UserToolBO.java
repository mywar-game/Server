package com.fantingame.game.msgbody.client.tool;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户道具对象**/
public class UserToolBO implements ICodeAble {

		/**道具id，即道具系统常量表中的唯一id**/
	private Integer toolId=0;
	/**道具数量**/
	private Integer toolNum=0;
	/**仓库数量**/
	private Integer storehouseNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(toolId);

		outputStream.writeInt(toolNum);

		outputStream.writeInt(storehouseNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toolId = inputStream.readInt();

		toolNum = inputStream.readInt();

		storehouseNum = inputStream.readInt();


	}
	
		/**道具id，即道具系统常量表中的唯一id**/
    public Integer getToolId() {
		return toolId;
	}
	/**道具id，即道具系统常量表中的唯一id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	/**道具数量**/
    public Integer getToolNum() {
		return toolNum;
	}
	/**道具数量**/
    public void setToolNum(Integer toolNum) {
		this.toolNum = toolNum;
	}
	/**仓库数量**/
    public Integer getStorehouseNum() {
		return storehouseNum;
	}
	/**仓库数量**/
    public void setStorehouseNum(Integer storehouseNum) {
		this.storehouseNum = storehouseNum;
	}

	
	
}
