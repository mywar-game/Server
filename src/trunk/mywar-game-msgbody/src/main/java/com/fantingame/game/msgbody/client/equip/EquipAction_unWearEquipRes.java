package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.equip.UserEquipBO;

/**卸下装备**/
public class EquipAction_unWearEquipRes implements ICodeAble {

		/**用户装备**/
	private UserEquipBO userEquipBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userEquipBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userEquipBO=new UserEquipBO();    
		userEquipBO.decode(inputStream);


	}
	
		/**用户装备**/
    public UserEquipBO getUserEquipBO() {
		return userEquipBO;
	}
	/**用户装备**/
    public void setUserEquipBO(UserEquipBO userEquipBO) {
		this.userEquipBO = userEquipBO;
	}

	
	
}
