package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import java.util.List;
import java.util.ArrayList;

/**装备镶嵌宝石换宝石、取下宝石**/
public class EquipAction_equipFillInGemstoneRes implements ICodeAble {

		/**用户宝石列表信息**/
	private List<UserGemstoneBO> userGemstoneBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userGemstoneBOList==null||userGemstoneBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userGemstoneBOList.size());
		}
		if(userGemstoneBOList!=null&&userGemstoneBOList.size()>0){
			for(int userGemstoneBOListi=0;userGemstoneBOListi<userGemstoneBOList.size();userGemstoneBOListi++){
				userGemstoneBOList.get(userGemstoneBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userGemstoneBOListSize = inputStream.readInt();
		if(userGemstoneBOListSize>0){
			userGemstoneBOList = new ArrayList<UserGemstoneBO>();
			for(int userGemstoneBOListi=0;userGemstoneBOListi<userGemstoneBOListSize;userGemstoneBOListi++){
				 UserGemstoneBO entry = new UserGemstoneBO();entry.decode(inputStream);userGemstoneBOList.add(entry);
			}
		}
	}
	
		/**用户宝石列表信息**/
    public List<UserGemstoneBO> getUserGemstoneBOList() {
		return userGemstoneBOList;
	}
	/**用户宝石列表信息**/
    public void setUserGemstoneBOList(List<UserGemstoneBO> userGemstoneBOList) {
		this.userGemstoneBOList = userGemstoneBOList;
	}

	
	
}
