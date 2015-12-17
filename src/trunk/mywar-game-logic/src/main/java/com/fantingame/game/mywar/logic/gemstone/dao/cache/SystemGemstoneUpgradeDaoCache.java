package com.fantingame.game.mywar.logic.gemstone.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneUpgrade;

public class SystemGemstoneUpgradeDaoCache extends StaticDataDaoBaseT<Integer, SystemGemstoneUpgrade> {

	@Override
	protected Integer getCacheKey(SystemGemstoneUpgrade v) {
		return v.getGemstoneId();
	}
	
	public SystemGemstoneUpgrade getSystemGemstoneUpgrade(int gemstoneId) {
		return super.getValue(gemstoneId);
	}
	
}
