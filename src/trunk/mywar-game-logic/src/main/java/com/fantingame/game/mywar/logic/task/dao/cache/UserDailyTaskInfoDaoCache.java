package com.fantingame.game.mywar.logic.task.dao.cache;

import java.util.Date;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.task.dao.UserDailyTaskInfoDao;
import com.fantingame.game.mywar.logic.task.dao.mysql.UserDailyTaskInfoDaoMysql;
import com.fantingame.game.mywar.logic.task.model.UserDailyTaskInfo;

public class UserDailyTaskInfoDaoCache extends BaseCacheDao<UserDailyTaskInfo>
		implements UserDailyTaskInfoDao {

	private UserDailyTaskInfoDaoMysql userDailyTaskInfoDaoMysql;

	@Override
	protected UserDailyTaskInfo loadFromDb(String userId) {
		return this.userDailyTaskInfoDaoMysql.getUserDailyTaskInfo(userId);
	}

	public UserDailyTaskInfoDaoMysql getUserDailyTaskInfoDaoMysql() {
		return userDailyTaskInfoDaoMysql;
	}

	public void setUserDailyTaskInfoDaoMysql(
			UserDailyTaskInfoDaoMysql userDailyTaskInfoDaoMysql) {
		this.userDailyTaskInfoDaoMysql = userDailyTaskInfoDaoMysql;
	}

	@Override
	public UserDailyTaskInfo getUserDailyTaskInfo(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public boolean addUserDailyTaskInfo(UserDailyTaskInfo userDailyTaskInfo) {
		if (this.userDailyTaskInfoDaoMysql.addUserDailyTaskInfo(userDailyTaskInfo)) {
			super.addEntry(userDailyTaskInfo.getUserId(), userDailyTaskInfo);		
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserDailyTaskInfo(String userId, int times, int systemTaskId) {
		if (this.userDailyTaskInfoDaoMysql.updateUserDailyTaskInfo(userId, times, systemTaskId)) {
			if (super.isExitKey(userId)) {
				UserDailyTaskInfo info = super.getEntry(userId);
				info.setTimes(times);
				info.setUpdatedTime(new Date());
				info.setSystemTaskId(systemTaskId);
			}
			
			return true;
		}
		
		return false;
	}

}
