package com.fantingame.game.mywar.logic.life.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.life.model.SystemWeatherEffect;

public class SystemWeatherEffectDaoCache extends StaticDataDaoBaseT<Integer, SystemWeatherEffect> {

	@Override
	protected Integer getCacheKey(SystemWeatherEffect v) {
		return v.getGroupId();
	}

	public List<SystemWeatherEffect> getSystemWeatherEffectList() {
		return super.getValueList();
	}
	
}
