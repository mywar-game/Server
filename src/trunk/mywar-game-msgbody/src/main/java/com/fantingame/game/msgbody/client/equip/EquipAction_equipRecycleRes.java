package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**回收**/
public class EquipAction_equipRecycleRes implements ICodeAble {

		/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**用户装备id**/
	private String userEquipId="";
	/**用户剩余金币**/
	private Integer gold=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		outputStream.writeUTF(userEquipId);

		outputStream.writeInt(gold);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		userEquipId = inputStream.readUTF();

		gold = inputStream.readInt();


	}
	
		/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
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
