package com.fantingame.game.mywar.logic.life.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.life.model.SystemWeatherMap;

public class SystemWeatherMapDaoCache extends StaticDataDaoBaseT<Integer, SystemWeatherMap> {

	@Override
	protected Integer getCacheKey(SystemWeatherMap v) {
		return v.getMapId();
	}
	
	public List<SystemWeatherMap> getSystemWeatherMapList() {
		return super.getValueList();
	}

}
