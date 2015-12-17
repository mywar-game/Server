package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.notify.user.UserBO;

/**获取用户阵容信息**/
public class HeroAction_getUserBattleInfoRes implements ICodeAble {

		/**用户英雄列表**/
	private List<UserHeroBO> userHeroList=null;
	/**英雄装备列表**/
	private List<UserEquipBO> userEquipList=null;
	/**用户宝石列表**/
	private List<UserGemstoneBO> userGemstoneList=null;
	/**用户对象**/
	private UserBO user=null;

	
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
        if(userGemstoneList==null||userGemstoneList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userGemstoneList.size());
		}
		if(userGemstoneList!=null&&userGemstoneList.size()>0){
			for(int userGemstoneListi=0;userGemstoneListi<userGemstoneList.size();userGemstoneListi++){
				userGemstoneList.get(userGemstoneListi).encode(outputStream);
			}
		}		user.encode(outputStream);


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
        int userGemstoneListSize = inputStream.readInt();
		if(userGemstoneListSize>0){
			userGemstoneList = new ArrayList<UserGemstoneBO>();
			for(int userGemstoneListi=0;userGemstoneListi<userGemstoneListSize;userGemstoneListi++){
				 UserGemstoneBO entry = new UserGemstoneBO();entry.decode(inputStream);userGemstoneList.add(entry);
			}
		}		user=new UserBO();    
		user.decode(inputStream);


	}
	
		/**用户英雄列表**/
    public List<UserHeroBO> getUserHeroList() {
		return userHeroList;
	}
	/**用户英雄列表**/
    public void setUserHeroList(List<UserHeroBO> userHeroList) {
		this.userHeroList = userHeroList;
	}
	/**英雄装备列表**/
    public List<UserEquipBO> getUserEquipList() {
		return userEquipList;
	}
	/**英雄装备列表**/
    public void setUserEquipList(List<UserEquipBO> userEquipList) {
		this.userEquipList = userEquipList;
	}
	/**用户宝石列表**/
    public List<UserGemstoneBO> getUserGemstoneList() {
		return userGemstoneList;
	}
	/**用户宝石列表**/
    public void setUserGemstoneList(List<UserGemstoneBO> userGemstoneList) {
		this.userGemstoneList = userGemstoneList;
	}
	/**用户对象**/
    public UserBO getUser() {
		return user;
	}
	/**用户对象**/
    public void setUser(UserBO user) {
		this.user = user;
	}

	
	
}
