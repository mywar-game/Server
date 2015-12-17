package com.fantingame.game.mywar.logic.scene.dao.cache;



import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.scene.model.SystemMap;

public class SystemMapDaoCache extends StaticDataDaoBaseT<Integer,SystemMap>{

	@Override
	protected Integer getCacheKey(SystemMap v) {
		return v.getMapId();
	}
    
	public SystemMap getSystemMap(int mapId){
		return super.getValue(mapId);
	}
}
