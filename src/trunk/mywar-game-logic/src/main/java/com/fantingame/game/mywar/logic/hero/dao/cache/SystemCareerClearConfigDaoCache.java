package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemCareerClearConfig;

public class SystemCareerClearConfigDaoCache extends StaticDataDaoBaseT<Integer, SystemCareerClearConfig> {

	@Override
	protected Integer getCacheKey(SystemCareerClearConfig v) {
		return v.getCareerId();
	}

	public SystemCareerClearConfig getSystemCareerClearConfig(int careerId) {
		return super.getValue(careerId);
	}
	
}
