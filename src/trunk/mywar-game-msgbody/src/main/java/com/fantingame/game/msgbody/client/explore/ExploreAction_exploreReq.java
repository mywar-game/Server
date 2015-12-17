package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**探索**/
public class ExploreAction_exploreReq implements ICodeAble {

		/**探索（左右）**/
	private Integer type=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(type);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		type = inputStream.readInt();


	}
	
		/**探索（左右）**/
    public Integer getType() {
		return type;
	}
	/**探索（左右）**/
    public void setType(Integer type) {
		this.type = type;
	}

	
	
}
