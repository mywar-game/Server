package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.hero.dao.UserCareerInfoDao;
import com.fantingame.game.mywar.logic.hero.dao.mysql.UserCareerInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.hero.model.UserCareerInfo;
import com.google.common.collect.Maps;

public class UserCareerInfoDaoCacheImpl extends BaseCacheMapDao<UserCareerInfo> implements
		UserCareerInfoDao {

	private UserCareerInfoDaoMysqlImpl userCareerInfoDaoMysqlImpl;
	
	public UserCareerInfoDaoMysqlImpl getUserCareerInfoDaoMysqlImpl() {
		return userCareerInfoDaoMysqlImpl;
	}

	public void setUserCareerInfoDaoMysqlImpl(UserCareerInfoDaoMysqlImpl userCareerInfoDaoMysqlImpl) {
		this.userCareerInfoDaoMysqlImpl = userCareerInfoDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserCareerInfo> loadFromDb(String userId) {
		List<UserCareerInfo> list = this.userCareerInfoDaoMysqlImpl.getUserCareerInfoList(userId);
		Map<String, UserCareerInfo> map = Maps.newConcurrentMap();
		for (UserCareerInfo info : list) {
			map.put(Integer.toString(info.getDetailedCareerId()), info);
		}
		
		return map;
	}

	@Override
	public List<UserCareerInfo> getUserCareerInfoList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean addList(String userId, List<UserCareerInfo> infoList) {
		if (this.userCareerInfoDaoMysqlImpl.addList(userId, infoList)) {
			for (UserCareerInfo info : infoList) {
				super.addMapValues(userId, Integer.toString(info.getDetailedCareerId()), info);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserCareerInfo getUserCareerInfo(String userId, int detailedCareerId) {
		return super.getByKey(userId, Integer.toString(detailedCareerId));
	}

	@Override
	public boolean updateUserCareerInfo(String userId, int detailedCareerId, int jobLevel) {
		if (this.userCareerInfoDaoMysqlImpl.updateUserCareerInfo(userId, detailedCareerId, jobLevel)) {
			if (super.isExitKey(userId)) {
				UserCareerInfo info = super.getByKey(userId, Integer.toString(detailedCareerId));
				info.setLevel(jobLevel);
				info.setUpdatedTime(new Date());
				
			}
			
			return true;
		}
		
		return false;
	}

}
