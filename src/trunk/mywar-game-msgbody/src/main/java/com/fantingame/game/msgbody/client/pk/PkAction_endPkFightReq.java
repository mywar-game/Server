package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**结束竞技场战斗**/
public class PkAction_endPkFightReq implements ICodeAble {

		/**胜负（0负1胜）**/
	private Integer flag=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(flag);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		flag = inputStream.readInt();


	}
	
		/**胜负（0负1胜）**/
    public Integer getFlag() {
		return flag;
	}
	/**胜负（0负1胜）**/
    public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
	
}
