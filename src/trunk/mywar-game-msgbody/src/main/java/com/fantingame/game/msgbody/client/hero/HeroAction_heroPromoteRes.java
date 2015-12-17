package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**英雄进阶**/
public class HeroAction_heroPromoteRes implements ICodeAble {

		/**用户英雄对象**/
	private UserHeroBO userHeroBO=null;
	/**消耗的用户装备id列表**/
	private List<String> userEquipIdList=null;
	/**消耗的用户宝石id列表**/
	private List<String> userGemstoneIdList=null;
	/**消耗的道具列表**/
	private List<GoodsBeanBO> toolList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userHeroBO.encode(outputStream);

		
        if(userEquipIdList==null||userEquipIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userEquipIdList.size());
		}
		if(userEquipIdList!=null&&userEquipIdList.size()>0){
			for(int userEquipIdListi=0;userEquipIdListi<userEquipIdList.size();userEquipIdListi++){
						outputStream.writeUTF(userEquipIdList.get(userEquipIdListi));


			}
		}		
        if(userGemstoneIdList==null||userGemstoneIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userGemstoneIdList.size());
		}
		if(userGemstoneIdList!=null&&userGemstoneIdList.size()>0){
			for(int userGemstoneIdListi=0;userGemstoneIdListi<userGemstoneIdList.size();userGemstoneIdListi++){
						outputStream.writeUTF(userGemstoneIdList.get(userGemstoneIdListi));


			}
		}		
        if(toolList==null||toolList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(toolList.size());
		}
		if(toolList!=null&&toolList.size()>0){
			for(int toolListi=0;toolListi<toolList.size();toolListi++){
				toolList.get(toolListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroBO=new UserHeroBO();    
		userHeroBO.decode(inputStream);

		
        int userEquipIdListSize = inputStream.readInt();
		if(userEquipIdListSize>0){
			userEquipIdList = new ArrayList<String>();
			for(int userEquipIdListi=0;userEquipIdListi<userEquipIdListSize;userEquipIdListi++){
				 userEquipIdList.add(inputStream.readUTF());
			}
		}		
        int userGemstoneIdListSize = inputStream.readInt();
		if(userGemstoneIdListSize>0){
			userGemstoneIdList = new ArrayList<String>();
			for(int userGemstoneIdListi=0;userGemstoneIdListi<userGemstoneIdListSize;userGemstoneIdListi++){
				 userGemstoneIdList.add(inputStream.readUTF());
			}
		}		
        int toolListSize = inputStream.readInt();
		if(toolListSize>0){
			toolList = new ArrayList<GoodsBeanBO>();
			for(int toolListi=0;toolListi<toolListSize;toolListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);toolList.add(entry);
			}
		}
	}
	
		/**用户英雄对象**/
    public UserHeroBO getUserHeroBO() {
		return userHeroBO;
	}
	/**用户英雄对象**/
    public void setUserHeroBO(UserHeroBO userHeroBO) {
		this.userHeroBO = userHeroBO;
	}
	/**消耗的用户装备id列表**/
    public List<String> getUserEquipIdList() {
		return userEquipIdList;
	}
	/**消耗的用户装备id列表**/
    public void setUserEquipIdList(List<String> userEquipIdList) {
		this.userEquipIdList = userEquipIdList;
	}
	/**消耗的用户宝石id列表**/
    public List<String> getUserGemstoneIdList() {
		return userGemstoneIdList;
	}
	/**消耗的用户宝石id列表**/
    public void setUserGemstoneIdList(List<String> userGemstoneIdList) {
		this.userGemstoneIdList = userGemstoneIdList;
	}
	/**消耗的道具列表**/
    public List<GoodsBeanBO> getToolList() {
		return toolList;
	}
	/**消耗的道具列表**/
    public void setToolList(List<GoodsBeanBO> toolList) {
		this.toolList = toolList;
	}

	
	
}
