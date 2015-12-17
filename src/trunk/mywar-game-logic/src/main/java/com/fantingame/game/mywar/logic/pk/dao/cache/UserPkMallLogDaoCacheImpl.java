package com.fantingame.game.mywar.logic.pk.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkMallLogDao;
import com.fantingame.game.mywar.logic.pk.dao.mysql.UserPkMallLogDaoMysqlImpl;
import com.fantingame.game.mywar.logic.pk.model.UserPkMallLog;
import com.google.common.collect.Maps;

public class UserPkMallLogDaoCacheImpl extends BaseCacheMapDao<UserPkMallLog>
		implements UserPkMallLogDao {

	private UserPkMallLogDaoMysqlImpl userPkMallLogDaoMysqlImpl;

	public UserPkMallLogDaoMysqlImpl getUserPkMallLogDaoMysqlImpl() {
		return userPkMallLogDaoMysqlImpl;
	}

	public void setUserPkMallLogDaoMysqlImpl(UserPkMallLogDaoMysqlImpl userPkMallLogDaoMysqlImpl) {
		this.userPkMallLogDaoMysqlImpl = userPkMallLogDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserPkMallLog> loadFromDb(String userId) {
		Map<String, UserPkMallLog> map = Maps.newConcurrentMap();
		List<UserPkMallLog> list = userPkMallLogDaoMysqlImpl.getUserPkMallLogList(userId);
		for (UserPkMallLog userPkMallLog : list) {
			map.put(Integer.toString(userPkMallLog.getMallId()), userPkMallLog);
		}

		return null;
	}

	@Override
	public List<UserPkMallLog> getUserPkMallLogList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean addList(List<UserPkMallLog> logList) {
		if (this.userPkMallLogDaoMysqlImpl.addList(logList)) {
			for (UserPkMallLog mallLog : logList) {
				super.addMapValues(mallLog.getUserId(), Integer.toString(mallLog.getMallId()), mallLog);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateAllList(String userId) {
		if (this.userPkMallLogDaoMysqlImpl.updateAllList(userId)) {
			if (super.isExitKey(userId)) {
				List<UserPkMallLog> logList = super.getMapList(userId);
				for (UserPkMallLog mallLog : logList) {
					mallLog.setDayBuyNum(0);
					mallLog.setUpdatedTime(new Date());
				}				
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserPkMallLog getUserPkMallLog(String userId, int mallId) {
		return super.getByKey(userId, Integer.toString(mallId));
	}

	@Override
	public boolean update(String userId, int mallId, int dayBuyNum, int totalBuyNum) {
		if (this.userPkMallLogDaoMysqlImpl.update(userId, mallId, dayBuyNum, totalBuyNum)) {
			if (super.isExitKey(userId)) {
				UserPkMallLog mallLog = super.getByKey(userId, Integer.toString(mallId));
				mallLog.setDayBuyNum(dayBuyNum);
				mallLog.setTotalBuyNum(totalBuyNum);
				mallLog.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
