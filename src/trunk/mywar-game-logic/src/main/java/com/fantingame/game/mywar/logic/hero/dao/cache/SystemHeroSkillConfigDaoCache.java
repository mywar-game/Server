package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroSkillConfig;

public class SystemHeroSkillConfigDaoCache extends StaticDataDaoBaseT<Integer, SystemHeroSkillConfig> {

	@Override
	protected Integer getCacheKey(SystemHeroSkillConfig v) {
		return v.getSystemHeroSkillId();
	}

	public SystemHeroSkillConfig getSystemHeroSkillConfig(Integer systemHeroSkillId) {
		return super.getValue(systemHeroSkillId);
	}
	
}
