package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**推送世界Boss结束信息**/
public class Boss_pushWorldBossDieNotify implements ICodeAble {

		/**0Boss死掉了1时间结束了**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		status = inputStream.readInt();


	}
	
		/**0Boss死掉了1时间结束了**/
    public Integer getStatus() {
		return status;
	}
	/**0Boss死掉了1时间结束了**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
