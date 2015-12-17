package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**出售**/
public class MallAction_sellReq implements ICodeAble {

		/**用户装备id**/
	private String userEquipId="";
	/**物品类型**/
	private Integer toolType=0;
	/**物品id**/
	private Integer toolId=0;
	/**物品数量**/
	private Integer toolNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userEquipId);

		outputStream.writeInt(toolType);

		outputStream.writeInt(toolId);

		outputStream.writeInt(toolNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userEquipId = inputStream.readUTF();

		toolType = inputStream.readInt();

		toolId = inputStream.readInt();

		toolNum = inputStream.readInt();


	}
	
		/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**物品类型**/
    public Integer getToolType() {
		return toolType;
	}
	/**物品类型**/
    public void setToolType(Integer toolType) {
		this.toolType = toolType;
	}
	/**物品id**/
    public Integer getToolId() {
		return toolId;
	}
	/**物品id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	/**物品数量**/
    public Integer getToolNum() {
		return toolNum;
	}
	/**物品数量**/
    public void setToolNum(Integer toolNum) {
		this.toolNum = toolNum;
	}

	
	
}
