package com.fantingame.game.msgbody.client.prestige;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**邀请英雄**/
public class PrestigeAction_inviteHeroRes implements ICodeAble {

		/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**金币**/
	private Integer gold=0;
	/**钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		outputStream.writeInt(gold);

		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		gold = inputStream.readInt();

		money = inputStream.readInt();


	}
	
		/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**金币**/
    public Integer getGold() {
		return gold;
	}
	/**金币**/
    public void setGold(Integer gold) {
		this.gold = gold;
	}
	/**钻石**/
    public Integer getMoney() {
		return money;
	}
	/**钻石**/
    public void setMoney(Integer money) {
		this.money = money;
	}

	
	
}
