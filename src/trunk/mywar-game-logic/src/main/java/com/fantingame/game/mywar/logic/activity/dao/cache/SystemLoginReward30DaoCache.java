package com.fantingame.game.mywar.logic.activity.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.activity.model.SystemLoginReward30;

public class SystemLoginReward30DaoCache extends StaticDataDaoBaseT<Integer, SystemLoginReward30> {

	@Override
	protected Integer getCacheKey(SystemLoginReward30 v) {
		return v.getDay();
	}

	public SystemLoginReward30 getSystemLoginReward30(int day) {
		return super.getValue(day);
	}
	
}
