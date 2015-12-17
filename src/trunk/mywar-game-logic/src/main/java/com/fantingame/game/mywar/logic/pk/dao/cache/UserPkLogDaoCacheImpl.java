package com.fantingame.game.mywar.logic.pk.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkLogDao;
import com.fantingame.game.mywar.logic.pk.dao.mysql.UserPkLogDaoMysqlImpl;
import com.fantingame.game.mywar.logic.pk.model.UserPkLog;
import com.google.common.collect.Lists;

public class UserPkLogDaoCacheImpl extends BaseCacheDao<List<UserPkLog>> implements
		UserPkLogDao {

	private UserPkLogDaoMysqlImpl userPkLogDaoMysqlImpl;

	public UserPkLogDaoMysqlImpl getUserPkLogDaoMysqlImpl() {
		return userPkLogDaoMysqlImpl;
	}

	public void setUserPkLogDaoMysqlImpl(UserPkLogDaoMysqlImpl userPkLogDaoMysqlImpl) {
		this.userPkLogDaoMysqlImpl = userPkLogDaoMysqlImpl;
	}

	@Override
	public boolean addUserPkLog(UserPkLog userPkLog) {
		if (this.userPkLogDaoMysqlImpl.addUserPkLog(userPkLog)) {
			if (super.isExitKey(userPkLog.getUserId())) {
				super.getEntry(userPkLog.getUserId()).add(userPkLog);
			} else {
				List<UserPkLog> list = Lists.newArrayList();
				list.add(userPkLog);
				super.addEntry(userPkLog.getUserId(), list);
			}
			
			if (super.isExitKey(userPkLog.getTargetUserId())) {
				super.getEntry(userPkLog.getTargetUserId()).add(userPkLog);
			} else {
				List<UserPkLog> list = Lists.newArrayList();
				list.add(userPkLog);
				super.addEntry(userPkLog.getTargetUserId(), list);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	protected List<UserPkLog> loadFromDb(String userId) {
		return this.userPkLogDaoMysqlImpl.getUserPkLogList(userId);
	}

	@Override
	public List<UserPkLog> getUserPkLogList(String userId) {
		return super.getEntry(userId);
	}

}
