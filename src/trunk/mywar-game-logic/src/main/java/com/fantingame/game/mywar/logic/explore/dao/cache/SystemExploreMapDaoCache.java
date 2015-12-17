package com.fantingame.game.mywar.logic.explore.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.explore.model.SystemExploreMap;

/**
 * 探索地图
 * 
 * @author yezp
 */
public class SystemExploreMapDaoCache extends StaticDataDaoBaseT<Integer, SystemExploreMap> {

	@Override
	protected Integer getCacheKey(SystemExploreMap v) {
		return v.getMapId();
	}
	
	public SystemExploreMap getSystemExploreMap(int mapId) {
		return super.getValue(mapId);
	}
	
	public List<SystemExploreMap> getSystemExploreMapList() {
		return super.getValueList();
	}
}
