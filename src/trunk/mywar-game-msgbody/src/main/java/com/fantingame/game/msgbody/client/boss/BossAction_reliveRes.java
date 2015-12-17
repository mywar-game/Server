package com.fantingame.game.msgbody.client.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**复活**/
public class BossAction_reliveRes implements ICodeAble {

		/**用户剩余钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		money = inputStream.readInt();


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
