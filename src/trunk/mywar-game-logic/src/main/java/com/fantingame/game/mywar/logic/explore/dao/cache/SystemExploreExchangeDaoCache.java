package com.fantingame.game.mywar.logic.explore.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.explore.model.SystemExploreExchange;

/**
 * 探索积分兑换英雄
 * 
 * @author yezp
 */
public class SystemExploreExchangeDaoCache extends StaticDataDaoBaseT<Integer, SystemExploreExchange> {

	@Override
	protected Integer getCacheKey(SystemExploreExchange v) {
		return v.getSystemHeroId();
	}

	public SystemExploreExchange getSystemExploreExchange(int systemHeroId) {
		return super.getValue(systemHeroId);
	}
	
}
