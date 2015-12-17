package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**兑换商品**/
public class PkAction_exchangeReq implements ICodeAble {

		/**商品id**/
	private Integer mallId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mallId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mallId = inputStream.readInt();


	}
	
		/**商品id**/
    public Integer getMallId() {
		return mallId;
	}
	/**商品id**/
    public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	
	
}
