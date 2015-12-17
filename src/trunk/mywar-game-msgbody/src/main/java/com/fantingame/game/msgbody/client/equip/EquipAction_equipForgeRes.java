package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;

/**锻造**/
public class EquipAction_equipForgeRes implements ICodeAble {

		/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**剩余钻石**/
	private Integer money=0;
	/**剩余金币**/
	private Integer gold=0;
	/**消耗的道具**/
	private List<GoodsBeanBO> goodsList=null;
	/**消耗掉的用户装备id列表**/
	private List<String> userEquipIdList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		outputStream.writeInt(money);

		outputStream.writeInt(gold);

		
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
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		money = inputStream.readInt();

		gold = inputStream.readInt();

		
        int goodsListSize = inputStream.readInt();
		if(goodsListSize>0){
			goodsList = new ArrayList<GoodsBeanBO>();
			for(int goodsListi=0;goodsListi<goodsListSize;goodsListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsList.add(entry);
			}
		}		
        int userEquipIdListSize = inputStream.readInt();
		if(userEquipIdListSize>0){
			userEquipIdList = new ArrayList<String>();
			for(int userEquipIdListi=0;userEquipIdListi<userEquipIdListSize;userEquipIdListi++){
				 userEquipIdList.add(inputStream.readUTF());
			}
		}
	}
	
		/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**剩余钻石**/
    public Integer getMoney() {
		return money;
	}
	/**剩余钻石**/
    public void setMoney(Integer money) {
		this.money = money;
	}
	/**剩余金币**/
    public Integer getGold() {
		return gold;
	}
	/**剩余金币**/
    public void setGold(Integer gold) {
		this.gold = gold;
	}
	/**消耗的道具**/
    public List<GoodsBeanBO> getGoodsList() {
		return goodsList;
	}
	/**消耗的道具**/
    public void setGoodsList(List<GoodsBeanBO> goodsList) {
		this.goodsList = goodsList;
	}
	/**消耗掉的用户装备id列表**/
    public List<String> getUserEquipIdList() {
		return userEquipIdList;
	}
	/**消耗掉的用户装备id列表**/
    public void setUserEquipIdList(List<String> userEquipIdList) {
		this.userEquipIdList = userEquipIdList;
	}

	
	
}
