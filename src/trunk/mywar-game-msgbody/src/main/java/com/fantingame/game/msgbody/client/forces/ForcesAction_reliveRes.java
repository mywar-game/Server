package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**复活**/
public class ForcesAction_reliveRes implements ICodeAble {

		/**用户剩余金币**/
	private Integer gold=0;
	/**用户剩余钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(gold);

		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		gold = inputStream.readInt();

		money = inputStream.readInt();


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
