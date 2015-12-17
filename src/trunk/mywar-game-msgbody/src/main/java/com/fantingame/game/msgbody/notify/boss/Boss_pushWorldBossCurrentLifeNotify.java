package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**推送世界Boss当前血量**/
public class Boss_pushWorldBossCurrentLifeNotify implements ICodeAble {

		/**Boss当前血量**/
	private Long currentLife=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeLong(currentLife);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		currentLife = inputStream.readLong();


	}
	
		/**Boss当前血量**/
    public Long getCurrentLife() {
		return currentLife;
	}
	/**Boss当前血量**/
    public void setCurrentLife(Long currentLife) {
		this.currentLife = currentLife;
	}

	
	
}
