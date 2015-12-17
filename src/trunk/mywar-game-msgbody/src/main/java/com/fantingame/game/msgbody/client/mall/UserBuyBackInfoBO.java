package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户回购信息对象**/
public class UserBuyBackInfoBO implements ICodeAble {

		/**回购id**/
	private String buyBackId="";
	/**物品类型**/
	private Integer toolType=0;
	/**物品id**/
	private Integer toolId=0;
	/**物品数量**/
	private Integer toolNum=0;
	/**装备主属性**/
	private String equipMainAtrr="";
	/**装备副属性**/
	private String equipSecondaryAttr="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(buyBackId);

		outputStream.writeInt(toolType);

		outputStream.writeInt(toolId);

		outputStream.writeInt(toolNum);

		outputStream.writeUTF(equipMainAtrr);

		outputStream.writeUTF(equipSecondaryAttr);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		buyBackId = inputStream.readUTF();

		toolType = inputStream.readInt();

		toolId = inputStream.readInt();

		toolNum = inputStream.readInt();

		equipMainAtrr = inputStream.readUTF();

		equipSecondaryAttr = inputStream.readUTF();


	}
	
		/**回购id**/
    public String getBuyBackId() {
		return buyBackId;
	}
	/**回购id**/
    public void setBuyBackId(String buyBackId) {
		this.buyBackId = buyBackId;
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
	/**装备主属性**/
    public String getEquipMainAtrr() {
		return equipMainAtrr;
	}
	/**装备主属性**/
    public void setEquipMainAtrr(String equipMainAtrr) {
		this.equipMainAtrr = equipMainAtrr;
	}
	/**装备副属性**/
    public String getEquipSecondaryAttr() {
		return equipSecondaryAttr;
	}
	/**装备副属性**/
    public void setEquipSecondaryAttr(String equipSecondaryAttr) {
		this.equipSecondaryAttr = equipSecondaryAttr;
	}

	
	
}
