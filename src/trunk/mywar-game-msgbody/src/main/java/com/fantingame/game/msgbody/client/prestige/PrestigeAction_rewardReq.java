package com.fantingame.game.msgbody.client.prestige;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取声望奖励**/
public class PrestigeAction_rewardReq implements ICodeAble {

		/**奖励id**/
	private Integer id=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(id);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		id = inputStream.readInt();


	}
	
		/**奖励id**/
    public Integer getId() {
		return id;
	}
	/**奖励id**/
    public void setId(Integer id) {
		this.id = id;
	}

	
	
}
