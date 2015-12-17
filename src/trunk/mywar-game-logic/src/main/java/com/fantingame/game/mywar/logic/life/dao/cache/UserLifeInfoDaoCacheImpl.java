package com.fantingame.game.mywar.logic.life.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.life.dao.UserLifeInfoDao;
import com.fantingame.game.mywar.logic.life.dao.mysql.UserLifeInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.life.model.UserLifeInfo;
import com.google.common.collect.Maps;

public class UserLifeInfoDaoCacheImpl extends BaseCacheMapDao<UserLifeInfo> implements
		UserLifeInfoDao {
	
	private UserLifeInfoDaoMysqlImpl userLifeInfoDaoMysqlImpl;

	@Override
	protected Map<String, UserLifeInfo> loadFromDb(String userId) {
		Map<String, UserLifeInfo> map = Maps.newConcurrentMap();
		List<UserLifeInfo> list = this.userLifeInfoDaoMysqlImpl.getUserLifeInfoList(userId);
		for (UserLifeInfo userLifeInfo : list) {
			map.put(userLifeInfo.getCategory() + "", userLifeInfo);
		}
		
		return map;
	}

	public UserLifeInfoDaoMysqlImpl getUserLifeInfoDaoMysqlImpl() {
		return userLifeInfoDaoMysqlImpl;
	}

	public void setUserLifeInfoDaoMysqlImpl(
			UserLifeInfoDaoMysqlImpl userLifeInfoDaoMysqlImpl) {
		this.userLifeInfoDaoMysqlImpl = userLifeInfoDaoMysqlImpl;
	}

	@Override
	public List<UserLifeInfo> getUserLifeInfoList(String userId) {		
		return super.getMapList(userId);
	}

	@Override
	public UserLifeInfo getUserLifeInfo(String userId, int category) {
		List<UserLifeInfo> list = super.getMapList(userId);
		for (UserLifeInfo info : list) {
			if (info.getCategory() == category)
				return info;
		}
		
		return null;
	}

	@Override
	public boolean addUserLifeInfo(UserLifeInfo userLifeInfo) {
		if (this.userLifeInfoDaoMysqlImpl.addUserLifeInfo(userLifeInfo)) {
			if (super.isExitKey(userLifeInfo.getUserId())) {
				super.addMapValues(userLifeInfo.getUserId(), userLifeInfo.getCategory() + "", userLifeInfo);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserLifeInfo(String userId, int category) {
		if (this.userLifeInfoDaoMysqlImpl.deleteUserLifeInfo(userId, category)) {
			if (super.isExitKey(userId)) {
				super.deleteKey(userId, category + "");
			}
			
			return true;
		}
		
		return false;
	}
	
}
