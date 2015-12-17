package com.fantingame.game.mywar.logic.forces.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.forces.model.SystemForces;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SystemForcesDaoCache extends StaticDataDaoBaseT<Integer, SystemForces> {
	
	private Map<Integer, List<SystemForces>> map = Maps.newConcurrentMap();
	
	/**
	 * 获取下一关卡
	 * 
	 * @param preSystemForces
	 * @return
	 */
	public SystemForces getNextSystemForces(int preSystemForces) {
		List<SystemForces> list = super.getValueList();
		for (SystemForces systemForces : list) {
			if (systemForces.getPreForcesId() == preSystemForces)
				return systemForces;
		}
		
		return null;
	}
	
	/**
	 * 获取系统关卡信息
	 * 
	 * @param forcesId
	 * @return
	 */
	public SystemForces getSystemForces(int forcesId) {
		return super.getValue(forcesId);
	}
	
	/**
	 * 获取该地图下的所有关卡
	 * 
	 * @param mapId
	 * @return
	 */
	public List<SystemForces> getSystemForcesList(int mapId) {
		if (map.containsKey(mapId))
			return map.get(mapId);
		
		List<SystemForces> list = super.getValueList();
		for (SystemForces forces : list) {
			if (map.containsKey(forces.getMapId())) {
				map.get(forces.getMapId()).add(forces);
			} else {
				List<SystemForces> l = Lists.newArrayList();
				l.add(forces);
				map.put(forces.getMapId(), l);
			}
		}
		
		return map.get(mapId);
	}
	
	/**
	 * 获取该副本下的副本列表
	 * 
	 * @param mapId
	 * @param bigForcesId
	 * @return
	 */
	public List<SystemForces> getSystemForcesList(int mapId, int bigForcesId) {
		List<SystemForces> list = getSystemForcesList(mapId);		
		List<SystemForces> systemForcesList = Lists.newArrayList();
		for (SystemForces systemForces : list) {
			if (systemForces.getBigForcesId() == bigForcesId)
				systemForcesList.add(systemForces);
		}
		
		return systemForcesList;
	}

	/**
	 * 获取该副本下的第一个关卡
	 * 
	 * @param mapId
	 * @param bigForcesId
	 * @return
	 */
	public SystemForces getFirstCopySystemForces(int mapId, int bigForcesId, int category) {
		List<SystemForces> systemForcesList = getSystemForcesList(mapId, bigForcesId);
		for (SystemForces systemForces : systemForcesList) {
			if (systemForces.getBigForcesId() == bigForcesId && systemForces.getPreForcesId() == 0
					&& systemForces.getForcesCategory() == category)
				return systemForces;
		}
		
		return null;
	}
	
	/**
	 * 根据地图获取采集点的信息
	 * 
	 * @param mapId
	 * @param category
	 * @return
	 */
	public List<SystemForces> getCollectListByMapId(int mapId, int category) {
		List<SystemForces> systemForcesList = getSystemForcesList(mapId);
		if (systemForcesList == null) {
			return null;
		}		
		
		List<SystemForces> list = Lists.newArrayList();
		for (SystemForces systemForces : systemForcesList) {
			if(systemForces.getForcesCategory() == category)
				list.add(systemForces);
		}
		
		return list;
	}
	
	@Override
	public void reload() {
		map.clear();
		super.reload();
	}
	
	@Override
	protected Integer getCacheKey(SystemForces v) {
		return v.getForcesId();
	}

}
