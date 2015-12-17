package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户捐献**/
public class LegionAction_userInvestReq implements ICodeAble {

		/**捐献的id**/
	private Integer id=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(id);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		id = inputStream.readInt();


	}
	
		/**捐献的id**/
    public Integer getId() {
		return id;
	}
	/**捐献的id**/
    public void setId(Integer id) {
		this.id = id;
	}

	
	
}
