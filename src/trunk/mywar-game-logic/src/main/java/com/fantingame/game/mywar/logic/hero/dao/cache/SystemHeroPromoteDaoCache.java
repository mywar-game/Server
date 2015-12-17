package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroPromote;

public class SystemHeroPromoteDaoCache extends StaticDataDaoBaseT<String, SystemHeroPromote> {

	@Override
	protected String getCacheKey(SystemHeroPromote v) {
		return v.getSystemHeroId() + "_" + v.getProSystemHeroId();
	}

	public SystemHeroPromote getSystemHeroPromote(int systemHeroId, int proSystemHeroId) {
		return super.getValue(systemHeroId + "_" + proSystemHeroId);
	}
	
}
