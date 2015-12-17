package com.fantingame.game.mywar.logic.boss.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.boss.model.SystemBossMap;

public class SystemBossMapDaoCache extends StaticDataDaoBaseT<Integer, SystemBossMap> {

	@Override
	protected Integer getCacheKey(SystemBossMap v) {
		return v.getMapId();
	}

	public List<SystemBossMap> getSystemBossMapList() {
		return super.getValueList();
	}
	
}
