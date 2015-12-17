package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户装备对象**/
public class UserEquipBO implements ICodeAble {

		/**用户编号**/
	private String userId="";
	/**用户装备唯一id**/
	private String userEquipId="";
	/**用户英雄唯一id**/
	private String userHeroId="";
	/**系统装备唯一id**/
	private Integer equipId=0;
	/**装备主属性**/
	private String equipMainAttr="";
	/**装备次级属性**/
	private String equipSecondaryAttr="";
	/**用户穿戴的位置**/
	private Integer pos=0;
	/**几个镶孔**/
	private Integer holeNum=0;
	/**装备附魔的属性**/
	private String magicEquipAttr="";
	/**仓库数量为1时，即该装备是在仓库中**/
	private Integer storehouseNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userEquipId);

		outputStream.writeUTF(userHeroId);

		outputStream.writeInt(equipId);

		outputStream.writeUTF(equipMainAttr);

		outputStream.writeUTF(equipSecondaryAttr);

		outputStream.writeInt(pos);

		outputStream.writeInt(holeNum);

		outputStream.writeUTF(magicEquipAttr);

		outputStream.writeInt(storehouseNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userEquipId = inputStream.readUTF();

		userHeroId = inputStream.readUTF();

		equipId = inputStream.readInt();

		equipMainAttr = inputStream.readUTF();

		equipSecondaryAttr = inputStream.readUTF();

		pos = inputStream.readInt();

		holeNum = inputStream.readInt();

		magicEquipAttr = inputStream.readUTF();

		storehouseNum = inputStream.readInt();


	}
	
		/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户装备唯一id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备唯一id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**用户英雄唯一id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**用户英雄唯一id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**系统装备唯一id**/
    public Integer getEquipId() {
		return equipId;
	}
	/**系统装备唯一id**/
    public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	/**装备主属性**/
    public String getEquipMainAttr() {
		return equipMainAttr;
	}
	/**装备主属性**/
    public void setEquipMainAttr(String equipMainAttr) {
		this.equipMainAttr = equipMainAttr;
	}
	/**装备次级属性**/
    public String getEquipSecondaryAttr() {
		return equipSecondaryAttr;
	}
	/**装备次级属性**/
    public void setEquipSecondaryAttr(String equipSecondaryAttr) {
		this.equipSecondaryAttr = equipSecondaryAttr;
	}
	/**用户穿戴的位置**/
    public Integer getPos() {
		return pos;
	}
	/**用户穿戴的位置**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}
	/**几个镶孔**/
    public Integer getHoleNum() {
		return holeNum;
	}
	/**几个镶孔**/
    public void setHoleNum(Integer holeNum) {
		this.holeNum = holeNum;
	}
	/**装备附魔的属性**/
    public String getMagicEquipAttr() {
		return magicEquipAttr;
	}
	/**装备附魔的属性**/
    public void setMagicEquipAttr(String magicEquipAttr) {
		this.magicEquipAttr = magicEquipAttr;
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
