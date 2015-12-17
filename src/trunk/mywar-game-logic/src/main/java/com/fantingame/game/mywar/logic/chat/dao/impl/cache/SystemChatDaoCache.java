package com.fantingame.game.mywar.logic.chat.dao.impl.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.chat.model.SystemChat;

public class SystemChatDaoCache extends StaticDataDaoBaseT<Integer, SystemChat> {

	@Override
	protected Integer getCacheKey(SystemChat v) {
		return v.getChatId();
	}
	
	public SystemChat getSystemChat(int chatId) {
		return super.getValue(chatId);
	}

}
