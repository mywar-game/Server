package com.fantingame.game.mywar.logic.message.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.message.model.SystemMessageTemplate;

public class SystemMessageTemplateDaoCache extends StaticDataDaoBaseT<Integer, SystemMessageTemplate> {

	@Override
	protected Integer getCacheKey(SystemMessageTemplate v) {
		return v.getType();
	}

	public SystemMessageTemplate getSystemMessageTemplate(int type) {
		return super.getValue(type);
	}
	
}
