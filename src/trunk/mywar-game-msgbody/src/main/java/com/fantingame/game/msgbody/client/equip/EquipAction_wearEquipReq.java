package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**穿戴装备**/
public class EquipAction_wearEquipReq implements ICodeAble {

		/**要穿戴的英雄id**/
	private String userHeroId="";
	/**用户装备id**/
	private String userEquipId="";
	/**位置**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userHeroId);

		outputStream.writeUTF(userEquipId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroId = inputStream.readUTF();

		userEquipId = inputStream.readUTF();

		pos = inputStream.readInt();


	}
	
		/**要穿戴的英雄id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**要穿戴的英雄id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**位置**/
    public Integer getPos() {
		return pos;
	}
	/**位置**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
