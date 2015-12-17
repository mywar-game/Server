package com.fantingame.game.mywar.logic.life.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.life.model.SystemLifeConfig;

public class SystemLifeConfigDaoCache extends StaticDataDaoBaseT<Integer, SystemLifeConfig> {

	@Override
	protected Integer getCacheKey(SystemLifeConfig v) {
		return v.getCategory();
	}
	
	public SystemLifeConfig getSystemLifeConfig(int category) {
		return super.getValue(category);
	}

}
