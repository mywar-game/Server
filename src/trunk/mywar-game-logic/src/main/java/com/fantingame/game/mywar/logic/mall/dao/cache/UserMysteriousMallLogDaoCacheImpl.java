package com.fantingame.game.mywar.logic.mall.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.mall.dao.UserMysteriousMallLogDao;
import com.fantingame.game.mywar.logic.mall.dao.mysql.UserMysteriousMallLogDaoMysqlImpl;
import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallLog;
import com.google.common.collect.Maps;

public class UserMysteriousMallLogDaoCacheImpl extends BaseCacheMapDao<UserMysteriousMallLog>
		implements UserMysteriousMallLogDao {

	private UserMysteriousMallLogDaoMysqlImpl userMysteriousMallLogDaoMysqlImpl;
	
	public UserMysteriousMallLogDaoMysqlImpl getUserMysteriousMallLogDaoMysqlImpl() {
		return userMysteriousMallLogDaoMysqlImpl;
	}

	public void setUserMysteriousMallLogDaoMysqlImpl(
			UserMysteriousMallLogDaoMysqlImpl userMysteriousMallLogDaoMysqlImpl) {
		this.userMysteriousMallLogDaoMysqlImpl = userMysteriousMallLogDaoMysqlImpl;
	}

	@Override
	public List<UserMysteriousMallLog> getUserMysteriousMallLogList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	protected Map<String, UserMysteriousMallLog> loadFromDb(String userId) {
		List<UserMysteriousMallLog> list = this.userMysteriousMallLogDaoMysqlImpl.getUserMysteriousMallLogList(userId);
		Map<String, UserMysteriousMallLog> map = Maps.newConcurrentMap();
		for (UserMysteriousMallLog mallLog : list) {
			map.put(mallLog.getMallId() + "", mallLog);
		}
		
		return map;
	}

	@Override
	public boolean addUserMysteriousLogList(List<UserMysteriousMallLog> mallLogList) {
		if (this.userMysteriousMallLogDaoMysqlImpl.addUserMysteriousLogList(mallLogList)) {
			for (UserMysteriousMallLog mallLog : mallLogList) {
				super.addMapValues(mallLog.getUserId(), mallLog.getMallId() + "", mallLog);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserMysteriousLogList(String userId) {
		if (this.userMysteriousMallLogDaoMysqlImpl.deleteUserMysteriousLogList(userId)) {
			super.delete(userId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserMysteriousLog(String userId, int mallId) {
		if (this.userMysteriousMallLogDaoMysqlImpl.deleteUserMysteriousLog(userId, mallId)) {
			super.deleteKey(userId, Integer.toString(mallId));
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean addUserMysteriousLog(UserMysteriousMallLog mallLog) {
		if (this.userMysteriousMallLogDaoMysqlImpl.addUserMysteriousLog(mallLog)) {
			super.addMapValues(mallLog.getUserId(), Integer.toString(mallLog.getMallId()), mallLog);
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserMysteriousMallLog getUserMysteriousMallLog(String userId, int mallId) {
		return super.getByKey(userId, Integer.toString(mallId));
	}

}
