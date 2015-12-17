package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**卸下装备**/
public class EquipAction_unWearEquipReq implements ICodeAble {

		/**用户装备id**/
	private String userEquipId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userEquipId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userEquipId = inputStream.readUTF();


	}
	
		/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}

	
	
}
