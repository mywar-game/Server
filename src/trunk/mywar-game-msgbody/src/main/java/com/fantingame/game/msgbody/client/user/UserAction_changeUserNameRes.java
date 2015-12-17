package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**更改昵称**/
public class UserAction_changeUserNameRes implements ICodeAble {

		/**更改的昵称**/
	private String name="";
	/**用户剩余钻石**/
	private Integer money=0;
	/**改名卡id,没有为0**/
	private Integer toolId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(name);

		outputStream.writeInt(money);

		outputStream.writeInt(toolId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		name = inputStream.readUTF();

		money = inputStream.readInt();

		toolId = inputStream.readInt();


	}
	
		/**更改的昵称**/
    public String getName() {
		return name;
	}
	/**更改的昵称**/
    public void setName(String name) {
		this.name = name;
	}
	/**用户剩余钻石**/
    public Integer getMoney() {
		return money;
	}
	/**用户剩余钻石**/
    public void setMoney(Integer money) {
		this.money = money;
	}
	/**改名卡id,没有为0**/
    public Integer getToolId() {
		return toolId;
	}
	/**改名卡id,没有为0**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	
	
}
