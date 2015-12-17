package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**英雄传承**/
public class HeroAction_heroInheritRes implements ICodeAble {

		/**用户英雄列表**/
	private List<UserHeroBO> userHeroList=null;
	/**用户剩余金币**/
	private Integer gold=0;
	/**用户剩余钻石**/
	private Integer money=0;
	/**消耗的道具**/
	private List<GoodsBeanBO> goodsList=null;

	
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
		}		outputStream.writeInt(gold);

		outputStream.writeInt(money);

		
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
		
        int userHeroListSize = inputStream.readInt();
		if(userHeroListSize>0){
			userHeroList = new ArrayList<UserHeroBO>();
			for(int userHeroListi=0;userHeroListi<userHeroListSize;userHeroListi++){
				 UserHeroBO entry = new UserHeroBO();entry.decode(inputStream);userHeroList.add(entry);
			}
		}		gold = inputStream.readInt();

		money = inputStream.readInt();

		
        int goodsListSize = inputStream.readInt();
		if(goodsListSize>0){
			goodsList = new ArrayList<GoodsBeanBO>();
			for(int goodsListi=0;goodsListi<goodsListSize;goodsListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsList.add(entry);
			}
		}
	}
	
		/**用户英雄列表**/
    public List<UserHeroBO> getUserHeroList() {
		return userHeroList;
	}
	/**用户英雄列表**/
    public void setUserHeroList(List<UserHeroBO> userHeroList) {
		this.userHeroList = userHeroList;
	}
	/**用户剩余金币**/
    public Integer getGold() {
		return gold;
	}
	/**用户剩余金币**/
    public void setGold(Integer gold) {
		this.gold = gold;
	}
	/**用户剩余钻石**/
    public Integer getMoney() {
		return money;
	}
	/**用户剩余钻石**/
    public void setMoney(Integer money) {
		this.money = money;
	}
	/**消耗的道具**/
    public List<GoodsBeanBO> getGoodsList() {
		return goodsList;
	}
	/**消耗的道具**/
    public void setGoodsList(List<GoodsBeanBO> goodsList) {
		this.goodsList = goodsList;
	}

	
	
}
