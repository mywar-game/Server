package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;

/**领取任务奖励**/
public class TaskAction_receiveTaskRes implements ICodeAble {

		/**得到的奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**上交的道具对象**/
	private GoodsBeanBO goods=null;
	/**扣除的用户装备id列表**/
	private List<String> userEquipIdList=null;
	/**扣除的用户宝石id列表**/
	private List<String> userGemstoneIdList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		goods.encode(outputStream);

		
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
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		goods=new GoodsBeanBO();    
		goods.decode(inputStream);

		
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
	}
	
		/**得到的奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**得到的奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**上交的道具对象**/
    public GoodsBeanBO getGoods() {
		return goods;
	}
	/**上交的道具对象**/
    public void setGoods(GoodsBeanBO goods) {
		this.goods = goods;
	}
	/**扣除的用户装备id列表**/
    public List<String> getUserEquipIdList() {
		return userEquipIdList;
	}
	/**扣除的用户装备id列表**/
    public void setUserEquipIdList(List<String> userEquipIdList) {
		this.userEquipIdList = userEquipIdList;
	}
	/**扣除的用户宝石id列表**/
    public List<String> getUserGemstoneIdList() {
		return userGemstoneIdList;
	}
	/**扣除的用户宝石id列表**/
    public void setUserGemstoneIdList(List<String> userGemstoneIdList) {
		this.userGemstoneIdList = userGemstoneIdList;
	}

	
	
}
