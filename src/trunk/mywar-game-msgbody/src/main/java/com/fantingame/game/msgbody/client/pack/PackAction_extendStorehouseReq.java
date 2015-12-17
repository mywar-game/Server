package com.fantingame.game.msgbody.client.pack;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**扩充仓库格子**/
public class PackAction_extendStorehouseReq implements ICodeAble {

		/**扩展几个格子**/
	private Integer extendNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(extendNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		extendNum = inputStream.readInt();


	}
	
		/**扩展几个格子**/
    public Integer getExtendNum() {
		return extendNum;
	}
	/**扩展几个格子**/
    public void setExtendNum(Integer extendNum) {
		this.extendNum = extendNum;
	}

	
	
}
