package com.fantingame.game.msgbody.client.gemstone;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**合成宝石**/
public class GemstoneAction_gemstoneForgeRes implements ICodeAble {

		/**状态：1开始2取消3完成**/
	private Integer status=0;
	/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**消耗掉的用户宝石id列表**/
	private List<String> userGemstoneIdList=null;
	/**消耗的道具**/
	private List<GoodsBeanBO> goodsList=null;
	/**用户剩余金币**/
	private Integer gold=0;
	/**用户剩余钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(status);

		drop.encode(outputStream);

		
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

		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		status = inputStream.readInt();

		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		
        int userGemstoneIdListSize = inputStream.readInt();
		if(userGemstoneIdListSize>0){
			userGemstoneIdList = new ArrayList<String>();
			for(int userGemstoneIdListi=0;userGemstoneIdListi<userGemstoneIdListSize;userGemstoneIdListi++){
				 userGemstoneIdList.add(inputStream.readUTF());
			}
		}		
        int goodsListSize = inputStream.readInt();
		if(goodsListSize>0){
			goodsList = new ArrayList<GoodsBeanBO>();
			for(int goodsListi=0;goodsListi<goodsListSize;goodsListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsList.add(entry);
			}
		}		gold = inputStream.readInt();

		money = inputStream.readInt();


	}
	
		/**状态：1开始2取消3完成**/
    public Integer getStatus() {
		return status;
	}
	/**状态：1开始2取消3完成**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**消耗掉的用户宝石id列表**/
    public List<String> getUserGemstoneIdList() {
		return userGemstoneIdList;
	}
	/**消耗掉的用户宝石id列表**/
    public void setUserGemstoneIdList(List<String> userGemstoneIdList) {
		this.userGemstoneIdList = userGemstoneIdList;
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
	/**用户剩余钻石**/
    public Integer getMoney() {
		return money;
	}
	/**用户剩余钻石**/
    public void setMoney(Integer money) {
		this.money = money;
	}

	
	
}
