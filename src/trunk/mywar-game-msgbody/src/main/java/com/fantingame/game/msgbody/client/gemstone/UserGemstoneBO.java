package com.fantingame.game.msgbody.client.gemstone;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户宝石对象**/
public class UserGemstoneBO implements ICodeAble {

		/**用户宝石唯一id**/
	private String userGemstoneId="";
	/**宝石镶嵌的用户装备id**/
	private String userEquipId="";
	/**系统宝石id**/
	private Integer gemstoneId=0;
	/**宝石属性（eg**/
	private String attr="";
	/**宝石位置**/
	private Integer pos=0;
	/**仓库数量为1时，即该装备是在仓库中**/
	private Integer storehouseNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userGemstoneId);

		outputStream.writeUTF(userEquipId);

		outputStream.writeInt(gemstoneId);

		outputStream.writeUTF(attr);

		outputStream.writeInt(pos);

		outputStream.writeInt(storehouseNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userGemstoneId = inputStream.readUTF();

		userEquipId = inputStream.readUTF();

		gemstoneId = inputStream.readInt();

		attr = inputStream.readUTF();

		pos = inputStream.readInt();

		storehouseNum = inputStream.readInt();


	}
	
		/**用户宝石唯一id**/
    public String getUserGemstoneId() {
		return userGemstoneId;
	}
	/**用户宝石唯一id**/
    public void setUserGemstoneId(String userGemstoneId) {
		this.userGemstoneId = userGemstoneId;
	}
	/**宝石镶嵌的用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**宝石镶嵌的用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**系统宝石id**/
    public Integer getGemstoneId() {
		return gemstoneId;
	}
	/**系统宝石id**/
    public void setGemstoneId(Integer gemstoneId) {
		this.gemstoneId = gemstoneId;
	}
	/**宝石属性（eg**/
    public String getAttr() {
		return attr;
	}
	/**宝石属性（eg**/
    public void setAttr(String attr) {
		this.attr = attr;
	}
	/**宝石位置**/
    public Integer getPos() {
		return pos;
	}
	/**宝石位置**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}
	/**仓库数量为1时，即该装备是在仓库中**/
    public Integer getStorehouseNum() {
		return storehouseNum;
	}
	/**仓库数量为1时，即该装备是在仓库中**/
    public void setStorehouseNum(Integer storehouseNum) {
		this.storehouseNum = storehouseNum;
	}

	
	
}
