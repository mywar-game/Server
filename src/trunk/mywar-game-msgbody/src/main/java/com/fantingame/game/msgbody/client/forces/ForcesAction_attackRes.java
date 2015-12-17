package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**攻击关卡**/
public class ForcesAction_attackRes implements ICodeAble {

		/**好友装备列表**/
	private List<UserEquipBO> userEquipList=null;
	/**即将获得的物品**/
	private List<GoodsBeanBO> goodsList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
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
        if(goodsList==null||goodsList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(goodsList.size());
		}
		if(goodsList!=null&&goodsList.size()>0){
			for(int goodsListi=0;goodsListi<goodsList.size();goodsListi++){
				goodsList.get(goodsListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userEquipListSize = inputStream.readInt();
		if(userEquipListSize>0){
			userEquipList = new ArrayList<UserEquipBO>();
			for(int userEquipListi=0;userEquipListi<userEquipListSize;userEquipListi++){
				 UserEquipBO entry = new UserEquipBO();entry.decode(inputStream);userEquipList.add(entry);
			}
		}		
        int goodsListSize = inputStream.readInt();
		if(goodsListSize>0){
			goodsList = new ArrayList<GoodsBeanBO>();
			for(int goodsListi=0;goodsListi<goodsListSize;goodsListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsList.add(entry);
			}
		}
	}
	
		/**好友装备列表**/
    public List<UserEquipBO> getUserEquipList() {
		return userEquipList;
	}
	/**好友装备列表**/
    public void setUserEquipList(List<UserEquipBO> userEquipList) {
		this.userEquipList = userEquipList;
	}
	/**即将获得的物品**/
    public List<GoodsBeanBO> getGoodsList() {
		return goodsList;
	}
	/**即将获得的物品**/
    public void setGoodsList(List<GoodsBeanBO> goodsList) {
		this.goodsList = goodsList;
	}

	
	
}
