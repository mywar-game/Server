package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**回收**/
public class EquipAction_equipRecycleReq implements ICodeAble {

		/**道具类型**/
	private Integer toolType=0;
	/**道具id**/
	private Integer toolId=0;
	/**用户装备id**/
	private String userEquipId="";
	/**状态：1开始分解2取消分解3完成分解**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(toolType);

		outputStream.writeInt(toolId);

		outputStream.writeUTF(userEquipId);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toolType = inputStream.readInt();

		toolId = inputStream.readInt();

		userEquipId = inputStream.readUTF();

		status = inputStream.readInt();


	}
	
		/**道具类型**/
    public Integer getToolType() {
		return toolType;
	}
	/**道具类型**/
    public void setToolType(Integer toolType) {
		this.toolType = toolType;
	}
	/**道具id**/
    public Integer getToolId() {
		return toolId;
	}
	/**道具id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**状态：1开始分解2取消分解3完成分解**/
    public Integer getStatus() {
		return status;
	}
	/**状态：1开始分解2取消分解3完成分解**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
