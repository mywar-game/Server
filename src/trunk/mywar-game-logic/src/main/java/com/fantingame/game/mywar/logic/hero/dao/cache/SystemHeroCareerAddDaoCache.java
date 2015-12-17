package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroCareerAdd;

public class SystemHeroCareerAddDaoCache extends StaticDataDaoBaseT<String, SystemHeroCareerAdd> {

	@Override
	protected String getCacheKey(SystemHeroCareerAdd v) {
		return v.getDetailedCareerId() + "_" + v.getLevel();
	}

}
