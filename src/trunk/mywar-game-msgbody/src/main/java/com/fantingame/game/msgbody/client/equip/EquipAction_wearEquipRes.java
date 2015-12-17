package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import java.util.List;
import java.util.ArrayList;

/**穿戴装备**/
public class EquipAction_wearEquipRes implements ICodeAble {

		/**用户装备列表**/
	private List<UserEquipBO> userEquipBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userEquipBOList==null||userEquipBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userEquipBOList.size());
		}
		if(userEquipBOList!=null&&userEquipBOList.size()>0){
			for(int userEquipBOListi=0;userEquipBOListi<userEquipBOList.size();userEquipBOListi++){
				userEquipBOList.get(userEquipBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userEquipBOListSize = inputStream.readInt();
		if(userEquipBOListSize>0){
			userEquipBOList = new ArrayList<UserEquipBO>();
			for(int userEquipBOListi=0;userEquipBOListi<userEquipBOListSize;userEquipBOListi++){
				 UserEquipBO entry = new UserEquipBO();entry.decode(inputStream);userEquipBOList.add(entry);
			}
		}
	}
	
		/**用户装备列表**/
    public List<UserEquipBO> getUserEquipBOList() {
		return userEquipBOList;
	}
	/**用户装备列表**/
    public void setUserEquipBOList(List<UserEquipBO> userEquipBOList) {
		this.userEquipBOList = userEquipBOList;
	}

	
	
}
