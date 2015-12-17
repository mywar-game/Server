package com.fantingame.game.mywar.logic.activity.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.activity.dao.UserActivityRewardLogDao;
import com.fantingame.game.mywar.logic.activity.dao.mysql.UserActivityRewardLogDaoMysqlImpl;
import com.fantingame.game.mywar.logic.activity.model.UserActivityRewardLog;
import com.google.common.collect.Maps;

public class UserActivityRewardLogDaoCacheImpl extends BaseCacheMapDao<UserActivityRewardLog>
		implements UserActivityRewardLogDao {

	private UserActivityRewardLogDaoMysqlImpl userActivityRewardLogDaoMysqlImpl;
	
	public UserActivityRewardLogDaoMysqlImpl getUserActivityRewardLogDaoMysqlImpl() {
		return userActivityRewardLogDaoMysqlImpl;
	}

	public void setUserActivityRewardLogDaoMysqlImpl(UserActivityRewardLogDaoMysqlImpl userActivityRewardLogDaoMysqlImpl) {
		this.userActivityRewardLogDaoMysqlImpl = userActivityRewardLogDaoMysqlImpl;
	}

	@Override
	public List<UserActivityRewardLog> getUserActivityRewardLogList(String userId) {		
		return super.getMapList(userId);
	}

	@Override
	protected Map<String, UserActivityRewardLog> loadFromDb(String userId) {
		Map<String, UserActivityRewardLog> map = Maps.newConcurrentMap();
		List<UserActivityRewardLog> logList = this.userActivityRewardLogDaoMysqlImpl.getUserActivityRewardLogList(userId);
		for (UserActivityRewardLog log : logList) {
			map.put(log.getActivityTaskRewardId() + "", log);
		}
		
		return map;
	}

	@Override
	public boolean deleteRewardLog(String userId) {
		if (this.userActivityRewardLogDaoMysqlImpl.deleteRewardLog(userId)) {
			super.delete(userId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean addUserActivityRewardLog(UserActivityRewardLog log) {
		if (this.userActivityRewardLogDaoMysqlImpl.addUserActivityRewardLog(log)) {
			super.addMapValues(log.getUserId(), log.getActivityTaskRewardId() + "", log);
			
			return true;
		}
		
		return false;
	}

	@Override
	public Map<String, UserActivityRewardLog> getUserActivityRewardLogMap(String userId) {
		return super.getMap(userId);
	}

	@Override
	public boolean addUserActivityRewardLogList(List<UserActivityRewardLog> logList) {
		if (this.userActivityRewardLogDaoMysqlImpl.addUserActivityRewardLogList(logList)) {
			for (UserActivityRewardLog userLog : logList) {
				super.addMapValues(userLog.getUserId(), userLog.getActivityTaskRewardId() + "", userLog);
			}
			return true;
		}
		
		return false;
	}

}
