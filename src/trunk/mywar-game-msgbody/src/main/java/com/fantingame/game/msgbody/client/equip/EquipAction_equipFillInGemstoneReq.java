package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**装备镶嵌宝石换宝石、取下宝石**/
public class EquipAction_equipFillInGemstoneReq implements ICodeAble {

		/**用户装备id**/
	private String userEquipId="";
	/**用户宝石id**/
	private String userGemstoneId="";
	/**镶嵌的位置(0取下,1号位，装备有几个空就几个位置)**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userEquipId);

		outputStream.writeUTF(userGemstoneId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userEquipId = inputStream.readUTF();

		userGemstoneId = inputStream.readUTF();

		pos = inputStream.readInt();


	}
	
		/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**用户宝石id**/
    public String getUserGemstoneId() {
		return userGemstoneId;
	}
	/**用户宝石id**/
    public void setUserGemstoneId(String userGemstoneId) {
		this.userGemstoneId = userGemstoneId;
	}
	/**镶嵌的位置(0取下,1号位，装备有几个空就几个位置)**/
    public Integer getPos() {
		return pos;
	}
	/**镶嵌的位置(0取下,1号位，装备有几个空就几个位置)**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
