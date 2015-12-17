package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroLevel;

public class SystemHeroLevelDaoCache extends StaticDataDaoBaseListT<Integer, SystemHeroLevel> {

	@Override
	protected Integer getCacheKey(SystemHeroLevel v) {
		return v.getColor();
	}

	public SystemHeroLevel getSystemHeroLevel(int color, int exp) {
		SystemHeroLevel systemHeroLevel = null;
		List<SystemHeroLevel> levelList = super.getValue(color);
		for (SystemHeroLevel level : levelList) {
			if (systemHeroLevel == null) {
				systemHeroLevel = level;
			} else {
				if (level.getExp() <= exp && level.getLevel() > systemHeroLevel.getLevel()) {
					systemHeroLevel = level;
				}
			}
		}
		
		return systemHeroLevel;
	}
	
	public SystemHeroLevel getSystemHeroLevelByLevel(int color, int level) {
		List<SystemHeroLevel> levelList = super.getValue(color);
		for (SystemHeroLevel heroLevel : levelList) {
			if (heroLevel.getLevel() == level)
				return heroLevel;
		}
		
		return null;
	}

}
