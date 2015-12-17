package com.fantingame.game.mywar.logic.activity.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.activity.dao.UserLoginReward30Dao;
import com.fantingame.game.mywar.logic.activity.dao.mysql.UserLoginReward30DaoMysqlImpl;
import com.fantingame.game.mywar.logic.activity.model.UserLoginReward30;
import com.google.common.collect.Maps;

public class UserLoginReward30DaoCacheImpl extends BaseCacheMapDao<UserLoginReward30> implements
		UserLoginReward30Dao {

	private UserLoginReward30DaoMysqlImpl userLoginReward30DaoMysqlImpl;
	
	public UserLoginReward30DaoMysqlImpl getUserLoginReward30DaoMysqlImpl() {
		return userLoginReward30DaoMysqlImpl;
	}

	public void setUserLoginReward30DaoMysqlImpl(UserLoginReward30DaoMysqlImpl userLoginReward30DaoMysqlImpl) {
		this.userLoginReward30DaoMysqlImpl = userLoginReward30DaoMysqlImpl;
	}
	
	@Override
	protected Map<String, UserLoginReward30> loadFromDb(String userId) {
		List<UserLoginReward30> list = this.userLoginReward30DaoMysqlImpl.getUserLoginReward30List(userId);
		Map<String, UserLoginReward30> map = Maps.newConcurrentMap();
		for (UserLoginReward30 reward : list) {
			map.put(reward.getDay() + "", reward);
		}
		
		return map;
	}

	@Override
	public List<UserLoginReward30> getUserLoginReward30List(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public UserLoginReward30 getLastLoginReward30(String userId) {
		List<UserLoginReward30> list = super.getMapList(userId);
		if (list.size() == 0)
			return null;
		
		UserLoginReward30 last = list.get(0);
		for (UserLoginReward30 loginReward : list) {
			if (loginReward.getDay() > last.getDay())
				last = loginReward;
		}		
		
		return last;
	}

	@Override
	public boolean addUserLoginReward30(UserLoginReward30 userLoginReward30) {
		if (this.userLoginReward30DaoMysqlImpl.addUserLoginReward30(userLoginReward30)) {
			super.addMapValues(userLoginReward30.getUserId(), userLoginReward30.getDay() + "", userLoginReward30);
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean deleteAll(String userId) {
		if (this.userLoginReward30DaoMysqlImpl.deleteAll(userId)) {
			super.delete(userId);			
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserLoginReward30 getUserLoginReward30(String userId, int day) {
		List<UserLoginReward30> list = super.getMapList(userId);
		for (UserLoginReward30 userLogin : list) {
			if (userLogin.getDay() == day)
				return userLogin;
		}
		
		return null;
	}

	@Override
	public boolean updateUserLoginReward30(String userId, int day, int status) {
		if (this.userLoginReward30DaoMysqlImpl.updateUserLoginReward30(userId, day, status)) {
			if (super.isExitKey(userId)) {
				UserLoginReward30 userLogin = super.getByKey(userId, day + "");
				userLogin.setStatus(status);
				userLogin.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
