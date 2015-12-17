package com.fantingame.game.msgbody.client.pawnshop;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取当铺信息**/
public class PawnshopAction_getPawnshopInfoReq implements ICodeAble {

		/**阵营**/
	private Integer camp=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(camp);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		camp = inputStream.readInt();


	}
	
		/**阵营**/
    public Integer getCamp() {
		return camp;
	}
	/**阵营**/
    public void setCamp(Integer camp) {
		this.camp = camp;
	}

	
	
}
