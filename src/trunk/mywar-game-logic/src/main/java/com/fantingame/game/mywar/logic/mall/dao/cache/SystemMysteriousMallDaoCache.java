package com.fantingame.game.mywar.logic.mall.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.mall.model.SystemMysteriousMall;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SystemMysteriousMallDaoCache extends StaticDataDaoBaseT<Integer, SystemMysteriousMall> {

	@Override
	protected Integer getCacheKey(SystemMysteriousMall v) {
		return v.getMallId();
	}
	
	public SystemMysteriousMall getSystemMysteriousMall(int mallId) {
		return super.getValue(mallId);
	}

	public List<SystemMysteriousMall> getSystemMysteriousMallList(int level) {
		List<SystemMysteriousMall> mallList = Lists.newArrayList();
		for (SystemMysteriousMall mall : super.getValueList()) {
			if (level >= mall.getMinLevel() && level <= mall.getMaxLevel())
				mallList.add(mall);
		}		
		
		return mallList;
	}
	
	public Map<Integer, SystemMysteriousMall> getSystemMysteriousMallMap(int level) {
		List<SystemMysteriousMall> mallList = getSystemMysteriousMallList(level);
		Map<Integer, SystemMysteriousMall> map = Maps.newConcurrentMap();
		
		for (SystemMysteriousMall mall : mallList) {
			map.put(mall.getMallId(), mall);
		}
		
		return map;
	}
	
}
