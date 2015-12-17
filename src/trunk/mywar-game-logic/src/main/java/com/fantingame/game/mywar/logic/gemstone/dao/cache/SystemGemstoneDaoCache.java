package com.fantingame.game.mywar.logic.gemstone.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstone;
import com.google.common.collect.Lists;

public class SystemGemstoneDaoCache extends StaticDataDaoBaseT<Integer, SystemGemstone> {

	@Override
	protected Integer getCacheKey(SystemGemstone v) {
		return v.getGemstoneId();
	}

	public SystemGemstone getSystemGemstone(int gemstoneId) {
		return super.getValue(gemstoneId);
	}
	
	public List<SystemGemstone> getSystemGemstoneList(int modelId, int level) {
		List<SystemGemstone> list = Lists.newArrayList();
		List<SystemGemstone> allList = super.getValueList();
		for (SystemGemstone stone : allList) {
			if (stone.getModelId() == modelId && stone.getLevel() <= level)
				list.add(stone);
		}		
		
		return list;
	}
	
}
