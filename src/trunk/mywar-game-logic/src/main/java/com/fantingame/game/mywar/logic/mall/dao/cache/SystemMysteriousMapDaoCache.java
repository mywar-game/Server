package com.fantingame.game.mywar.logic.mall.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.mall.model.SystemMysteriousMap;

public class SystemMysteriousMapDaoCache extends StaticDataDaoBaseT<Integer, SystemMysteriousMap> {

	@Override
	protected Integer getCacheKey(SystemMysteriousMap v) {		
		return v.getMapId();
	}

	public List<SystemMysteriousMap> getAllList() {
		return super.getValueList();
	}
	
}
