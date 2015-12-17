package com.fantingame.game.mywar.logic.forces.dao.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.forces.dao.UserForcesDao;
import com.fantingame.game.mywar.logic.forces.dao.mysql.UserForcesDaoMysqlImpl;
import com.fantingame.game.mywar.logic.forces.model.UserForces;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserForcesDaoCacheImpl extends BaseCacheMapDao<List<UserForces>> implements UserForcesDao {

	private UserForcesDaoMysqlImpl userForcesDaoMysqlImpl;
	@Override
	public List<UserForces> getUserForcesList(String userId) {
		Collection<List<UserForces>> list = super.getMapValues(userId);
		List<UserForces> result = Lists.newArrayList();
		for(List<UserForces> tempList:list){
			result.addAll(tempList);
		}
		return result;
	}
	
	@Override
	public boolean updateForcesTimes(String userId,int mapId, int forcesId, int times, int forcesType) {
		if(userForcesDaoMysqlImpl.updateForcesTimes(userId, mapId, forcesId, times, forcesType)){
			if(super.isExitKey(userId)){
				UserForces userForces = this.getUserForces(userId, mapId, forcesId, forcesType);
				userForces.setTimes(times);
				userForces.setUpdatedTime(new Date());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addUserForces(UserForces userForces) {
		if(userForcesDaoMysqlImpl.addUserForces(userForces)){
			if(super.isExitKey(userForces.getUserId())){
				List<UserForces> list = super.getByKey(userForces.getUserId(), userForces.getMapId()+"");
				if(list==null){
					list = Lists.newArrayList();
					list.add(userForces);
					super.addMapValues(userForces.getUserId(), userForces.getMapId()+"",list);
				}else{
					list.add(userForces);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public UserForces getUserForces(String userId, int mapId, int forcesId, int forcesType) {
		List<UserForces> list = super.getByKey(userId, mapId + "");
		if (list != null && list.size() > 0) {
			for(UserForces userForces : list) {
				if(userForces.getForcesId() == forcesId && userForces.getForcesType() == forcesType) {
					 return userForces;
				}
			}
        }
		return null;
	}

	@Override
	public List<UserForces> getUserForcesListByBigForcesId(String userId, int mapId,
			int bigForcesId) {
		List<UserForces> userForcesList = super.getByKey(userId, mapId + "");
		if (userForcesList == null || userForcesList.size() == 0)
			return null;

		List<UserForces> list = Lists.newArrayList();
		for (UserForces userForces : userForcesList) {
			if (userForces.getBigForcesId() == bigForcesId) {
				list.add(userForces);
			}
		}		
		
		return list;
	}
	
	@Override
	protected Map<String, List<UserForces>> loadFromDb(String key) {
		List<UserForces> list = userForcesDaoMysqlImpl.getUserForcesList(key);
		Map<String, List<UserForces>> map = Maps.newConcurrentMap();
		for (UserForces userForces:list) {
			String entryKey = userForces.getMapId() + "";
			if (map.containsKey(entryKey)) {
				map.get(entryKey).add(userForces);
			} else {
				List<UserForces> entry = Lists.newArrayList();
				entry.add(userForces);
				map.put(entryKey, entry);
			}
		}
		return map;
	}
	
	@Override
	public List<UserForces> getUserForcesListByMapId(String userId, int mapId) {
		return super.getByKey(userId, mapId + "");
	}
	
	public Map<Integer, UserForces> getUserForcesMapByMapId(String userId, int mapId) {
		List<UserForces> userForcesList = super.getByKey(userId, mapId + "");
		Map<Integer, UserForces> map = Maps.newConcurrentMap();
		if (userForcesList == null || userForcesList.size() == 0)
			return map;
		
		for (UserForces userForces : userForcesList) {
			map.put(userForces.getForcesId(), userForces);
		}
		
		return map;
	}
	
	public UserForcesDaoMysqlImpl getUserForcesDaoMysqlImpl() {
		return userForcesDaoMysqlImpl;
	}
	
	public void setUserForcesDaoMysqlImpl(
			UserForcesDaoMysqlImpl userForcesDaoMysqlImpl) {
		this.userForcesDaoMysqlImpl = userForcesDaoMysqlImpl;
	}
	
	@Override
	public boolean updateUserForcesStatus(String userId, int mapId, int forcesId, int status, int attackTimes, int forcesType) {
		if(userForcesDaoMysqlImpl.updateUserForcesStatus(userId, mapId, forcesId, status, attackTimes, forcesType)){
			if(super.isExitKey(userId)){
				UserForces userForces = this.getUserForces(userId, mapId, forcesId, forcesType);
				userForces.setStatus(status);
				userForces.setTimes(attackTimes);
				userForces.setUpdatedTime(new Date());
			}
			return true;
		}
		return false;
	}
}
