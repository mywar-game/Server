package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;

/**开始竞技场战斗**/
public class PkAction_startPkFightRes implements ICodeAble {

		/**用户防守方英雄列表**/
	private List<UserHeroBO> userHeroList=null;
	/**用户装备列表**/
	private List<UserEquipBO> userEquipList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userHeroList==null||userHeroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userHeroList.size());
		}
		if(userHeroList!=null&&userHeroList.size()>0){
			for(int userHeroListi=0;userHeroListi<userHeroList.size();userHeroListi++){
				userHeroList.get(userHeroListi).encode(outputStream);
			}
		}		
        if(userEquipList==null||userEquipList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userEquipList.size());
		}
		if(userEquipList!=null&&userEquipList.size()>0){
			for(int userEquipListi=0;userEquipListi<userEquipList.size();userEquipListi++){
				userEquipList.get(userEquipListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userHeroListSize = inputStream.readInt();
		if(userHeroListSize>0){
			userHeroList = new ArrayList<UserHeroBO>();
			for(int userHeroListi=0;userHeroListi<userHeroListSize;userHeroListi++){
				 UserHeroBO entry = new UserHeroBO();entry.decode(inputStream);userHeroList.add(entry);
			}
		}		
        int userEquipListSize = inputStream.readInt();
		if(userEquipListSize>0){
			userEquipList = new ArrayList<UserEquipBO>();
			for(int userEquipListi=0;userEquipListi<userEquipListSize;userEquipListi++){
				 UserEquipBO entry = new UserEquipBO();entry.decode(inputStream);userEquipList.add(entry);
			}
		}
	}
	
		/**用户防守方英雄列表**/
    public List<UserHeroBO> getUserHeroList() {
		return userHeroList;
	}
	/**用户防守方英雄列表**/
    public void setUserHeroList(List<UserHeroBO> userHeroList) {
		this.userHeroList = userHeroList;
	}
	/**用户装备列表**/
    public List<UserEquipBO> getUserEquipList() {
		return userEquipList;
	}
	/**用户装备列表**/
    public void setUserEquipList(List<UserEquipBO> userEquipList) {
		this.userEquipList = userEquipList;
	}

	
	
}
