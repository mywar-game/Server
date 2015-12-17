package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;

/**装备附魔**/
public class EquipAction_equipMagicRes implements ICodeAble {

		/**用户装备信息**/
	private UserEquipBO userEquipBO=null;
	/**消耗的道具**/
	private List<GoodsBeanBO> goodsList=null;
	/**用户剩余金币**/
	private Integer gold=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userEquipBO.encode(outputStream);

		
        if(goodsList==null||goodsList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(goodsList.size());
		}
		if(goodsList!=null&&goodsList.size()>0){
			for(int goodsListi=0;goodsListi<goodsList.size();goodsListi++){
				goodsList.get(goodsListi).encode(outputStream);
			}
		}		outputStream.writeInt(gold);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userEquipBO=new UserEquipBO();    
		userEquipBO.decode(inputStream);

		
        int goodsListSize = inputStream.readInt();
		if(goodsListSize>0){
			goodsList = new ArrayList<GoodsBeanBO>();
			for(int goodsListi=0;goodsListi<goodsListSize;goodsListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsList.add(entry);
			}
		}		gold = inputStream.readInt();


	}
	
		/**用户装备信息**/
    public UserEquipBO getUserEquipBO() {
		return userEquipBO;
	}
	/**用户装备信息**/
    public void setUserEquipBO(UserEquipBO userEquipBO) {
		this.userEquipBO = userEquipBO;
	}
	/**消耗的道具**/
    public List<GoodsBeanBO> getGoodsList() {
		return goodsList;
	}
	/**消耗的道具**/
    public void setGoodsList(List<GoodsBeanBO> goodsList) {
		this.goodsList = goodsList;
	}
	/**用户剩余金币**/
    public Integer getGold() {
		return gold;
	}
	/**用户剩余金币**/
    public void setGold(Integer gold) {
		this.gold = gold;
	}

	
	
}
