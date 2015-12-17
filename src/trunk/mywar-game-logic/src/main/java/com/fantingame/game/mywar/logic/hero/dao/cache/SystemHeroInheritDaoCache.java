package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroInherit;

public class SystemHeroInheritDaoCache extends StaticDataDaoBaseT<Integer, SystemHeroInherit> {

	@Override
	protected Integer getCacheKey(SystemHeroInherit v) {
		return v.getStar();
	}
	
	public SystemHeroInherit getSystemHeroInherit(int star) {
		return super.getValue(star);
	}

}
