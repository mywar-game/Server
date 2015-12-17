package com.fantingame.game.mywar.logic.hero.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroPromoteStar;

public class SystemHeroPromoteStarDaoCache extends StaticDataDaoBaseT<String, SystemHeroPromoteStar> {

	public SystemHeroPromoteStar getSystemHeroPromoteStar(int type, int star) {
		return super.getValue(type + "_" + star);		
	}

	@Override
	protected String getCacheKey(SystemHeroPromoteStar v) {
		return v.getType() + "_" + v.getStar();
	}
	
}
