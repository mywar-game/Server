package com.fantingame.game.msgbody.client.pack;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**扩充仓库格子**/
public class PackAction_extendStorehouseRes implements ICodeAble {

		/**扩展几个格子**/
	private Integer extendNum=0;
	/**用户剩余钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(extendNum);

		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		extendNum = inputStream.readInt();

		money = inputStream.readInt();


	}
	
		/**扩展几个格子**/
    public Integer getExtendNum() {
		return extendNum;
	}
	/**扩展几个格子**/
    public void setExtendNum(Integer extendNum) {
		this.extendNum = extendNum;
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
