package com.fantingame.game.msgbody.client.gemstone;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**合成宝石**/
public class GemstoneAction_gemstoneForgeReq implements ICodeAble {

		/**1宝石合成3矿石切割**/
	private Integer forgeType=0;
	/**道具id**/
	private Integer toolId=0;
	/**道具类型**/
	private Integer toolType=0;
	/**状态：1开始2取消3完成**/
	private Integer status=0;
	/**材料（只有在“其他”里面需要传入）**/
	private String material="";
	/**数量（默认传入1只有在“其他”里面用户选择输入）**/
	private Integer num=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(forgeType);

		outputStream.writeInt(toolId);

		outputStream.writeInt(toolType);

		outputStream.writeInt(status);

		outputStream.writeUTF(material);

		outputStream.writeInt(num);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		forgeType = inputStream.readInt();

		toolId = inputStream.readInt();

		toolType = inputStream.readInt();

		status = inputStream.readInt();

		material = inputStream.readUTF();

		num = inputStream.readInt();


	}
	
		/**1宝石合成3矿石切割**/
    public Integer getForgeType() {
		return forgeType;
	}
	/**1宝石合成3矿石切割**/
    public void setForgeType(Integer forgeType) {
		this.forgeType = forgeType;
	}
	/**道具id**/
    public Integer getToolId() {
		return toolId;
	}
	/**道具id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	/**道具类型**/
    public Integer getToolType() {
		return toolType;
	}
	/**道具类型**/
    public void setToolType(Integer toolType) {
		this.toolType = toolType;
	}
	/**状态：1开始2取消3完成**/
    public Integer getStatus() {
		return status;
	}
	/**状态：1开始2取消3完成**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**材料（只有在“其他”里面需要传入）**/
    public String getMaterial() {
		return material;
	}
	/**材料（只有在“其他”里面需要传入）**/
    public void setMaterial(String material) {
		this.material = material;
	}
	/**数量（默认传入1只有在“其他”里面用户选择输入）**/
    public Integer getNum() {
		return num;
	}
	/**数量（默认传入1只有在“其他”里面用户选择输入）**/
    public void setNum(Integer num) {
		this.num = num;
	}

	
	
}
