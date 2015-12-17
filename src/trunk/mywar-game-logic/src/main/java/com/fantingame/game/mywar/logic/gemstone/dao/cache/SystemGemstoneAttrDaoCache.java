package com.fantingame.game.mywar.logic.gemstone.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneAttr;

public class SystemGemstoneAttrDaoCache extends StaticDataDaoBaseListT<Integer, SystemGemstoneAttr> {

	@Override
	protected Integer getCacheKey(SystemGemstoneAttr v) {
		return v.getGemstoneId();
	}

	public List<SystemGemstoneAttr> getSystemGemstoneAttrList(int gemstoneId) {
		return super.getValue(gemstoneId);
	}
	
}
