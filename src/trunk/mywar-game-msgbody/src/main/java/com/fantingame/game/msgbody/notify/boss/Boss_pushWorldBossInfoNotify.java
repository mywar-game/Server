package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.boss.WorldBossInfoBO;

/**推送世界Boss信息**/
public class Boss_pushWorldBossInfoNotify implements ICodeAble {

		/**世界boss信息**/
	private WorldBossInfoBO worldBossInfoBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		worldBossInfoBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		worldBossInfoBO=new WorldBossInfoBO();    
		worldBossInfoBO.decode(inputStream);


	}
	
		/**世界boss信息**/
    public WorldBossInfoBO getWorldBossInfoBO() {
		return worldBossInfoBO;
	}
	/**世界boss信息**/
    public void setWorldBossInfoBO(WorldBossInfoBO worldBossInfoBO) {
		this.worldBossInfoBO = worldBossInfoBO;
	}

	
	
}
