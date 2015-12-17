package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取用户贡献的信息**/
public class LegionAction_getUserInvestInfoRes implements ICodeAble {

		/**0未贡献1已贡献**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		status = inputStream.readInt();


	}
	
		/**0未贡献1已贡献**/
    public Integer getStatus() {
		return status;
	}
	/**0未贡献1已贡献**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
