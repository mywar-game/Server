package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemCareerClear;

public class SystemCareerClearDaoCache extends StaticDataDaoBaseT<Integer, SystemCareerClear> {

	@Override
	protected Integer getCacheKey(SystemCareerClear v) {
		return v.getDetailedCareerId();
	}

	public List<SystemCareerClear> getAllList() {
		return super.getValueList();
	}
	
	public SystemCareerClear getSystemCareerClear(int detailedCareerId) {
		return super.getValue(detailedCareerId);
	}
}
