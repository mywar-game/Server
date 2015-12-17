package com.fantingame.game.mywar.logic.activity.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.activity.dao.UserActivityTaskDao;
import com.fantingame.game.mywar.logic.activity.dao.mysql.UserActivityTaskDaoMysqlImpl;
import com.fantingame.game.mywar.logic.activity.model.UserActivityTask;
import com.google.common.collect.Maps;

public class UserActivityTaskDaoCacheImpl extends BaseCacheMapDao<UserActivityTask> implements
		UserActivityTaskDao {

	private UserActivityTaskDaoMysqlImpl userActivityTaskDaoMysqlImpl;
	
	public UserActivityTaskDaoMysqlImpl getUserActivityTaskDaoMysqlImpl() {
		return userActivityTaskDaoMysqlImpl;
	}

	public void setUserActivityTaskDaoMysqlImpl(UserActivityTaskDaoMysqlImpl userActivityTaskDaoMysqlImpl) {
		this.userActivityTaskDaoMysqlImpl = userActivityTaskDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserActivityTask> loadFromDb(String userId) {
		List<UserActivityTask> list = userActivityTaskDaoMysqlImpl.getUserActivityTaskList(userId);
		Map<String, UserActivityTask> map = Maps.newConcurrentMap();
		for (UserActivityTask userActivityTask : list) {
			map.put(userActivityTask.getActivityTaskId() + "", userActivityTask);
		}
		
		return map;
	}

	@Override
	public List<UserActivityTask> getUserActivityTaskList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean deleteUserActivityTaskList(String userId) {
		if (this.userActivityTaskDaoMysqlImpl.deleteUserActivityTaskList(userId)) {
			super.delete(userId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserActivityTask getUserActivityTask(String userId, int activityTaskId) {
		return super.getByKey(userId, activityTaskId + "");
	}

	@Override
	public boolean addUserActivityTaskList(String userId, List<UserActivityTask> userTaskList) {
		if (this.userActivityTaskDaoMysqlImpl.addUserActivityTaskList(userId, userTaskList)) {
			for (UserActivityTask activityTask : userTaskList) {
				super.addMapValues(userId, activityTask.getActivityTaskId() + "", activityTask);
			}			
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserActivityTask(String userId, int activityTaskId, int finishTimes, int status) {
		if (this.userActivityTaskDaoMysqlImpl.updateUserActivityTask(userId, activityTaskId, finishTimes, status)) {
			if (super.isExitKey(userId)) {
				UserActivityTask userActivityTask = super.getByKey(userId, activityTaskId + "");
				userActivityTask.setFinishTimes(finishTimes);
				userActivityTask.setStatus(status);
				userActivityTask.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
