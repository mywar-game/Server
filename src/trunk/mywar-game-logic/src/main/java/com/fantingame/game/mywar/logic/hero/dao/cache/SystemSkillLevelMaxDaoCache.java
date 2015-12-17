package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemSkillLevelMax;

public class SystemSkillLevelMaxDaoCache extends StaticDataDaoBaseT<Integer, SystemSkillLevelMax> {

	@Override
	protected Integer getCacheKey(SystemSkillLevelMax v) {
		return v.getColor();
	}
	
	public SystemSkillLevelMax getSystemSkillLevelMax(int color) {
		return super.getValue(color);
	}

}
