package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**战斗后的翻牌**/
public class ForcesAction_openBattleBoxReq implements ICodeAble {

		/**状态1不开启2开启宝箱**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		status = inputStream.readInt();


	}
	
		/**状态1不开启2开启宝箱**/
    public Integer getStatus() {
		return status;
	}
	/**状态1不开启2开启宝箱**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
