package com.fantingame.game.msgbody.client.gemstone;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**宝石精炼升级**/
public class GemstoneAction_gemstoneUpgradeRes implements ICodeAble {

		/**用户宝石信息**/
	private UserGemstoneBO userGemstoneBO=null;
	/**扣除的用户宝石id**/
	private List<String> userGemstoneIdList=null;
	/**消耗的道具**/
	private List<GoodsBeanBO> goodsList=null;
	/**状态：1开始2取消3完成**/
	private Integer status=0;
	/**用户剩余金币**/
	private Integer gold=0;
	/**用户剩余钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userGemstoneBO.encode(outputStream);

		
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
		}		outputStream.writeInt(status);

		outputStream.writeInt(gold);

		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userGemstoneBO=new UserGemstoneBO();    
		userGemstoneBO.decode(inputStream);

		
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
		}		status = inputStream.readInt();

		gold = inputStream.readInt();

		money = inputStream.readInt();


	}
	
		/**用户宝石信息**/
    public UserGemstoneBO getUserGemstoneBO() {
		return userGemstoneBO;
	}
	/**用户宝石信息**/
    public void setUserGemstoneBO(UserGemstoneBO userGemstoneBO) {
		this.userGemstoneBO = userGemstoneBO;
	}
	/**扣除的用户宝石id**/
    public List<String> getUserGemstoneIdList() {
		return userGemstoneIdList;
	}
	/**扣除的用户宝石id**/
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
	/**状态：1开始2取消3完成**/
    public Integer getStatus() {
		return status;
	}
	/**状态：1开始2取消3完成**/
    public void setStatus(Integer status) {
		this.status = status;
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
