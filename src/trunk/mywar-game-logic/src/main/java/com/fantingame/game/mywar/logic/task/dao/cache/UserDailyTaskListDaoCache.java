package com.fantingame.game.mywar.logic.task.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.task.dao.UserDailyTaskListDao;
import com.fantingame.game.mywar.logic.task.dao.mysql.UserDailyTaskListDaoMysql;
import com.fantingame.game.mywar.logic.task.model.UserDailyTaskList;
import com.google.common.collect.Maps;

public class UserDailyTaskListDaoCache extends BaseCacheMapDao<UserDailyTaskList> implements
		UserDailyTaskListDao {
	
	private UserDailyTaskListDaoMysql userDailyTaskListDaoMysql;

	public UserDailyTaskListDaoMysql getUserDailyTaskListDaoMysql() {
		return userDailyTaskListDaoMysql;
	}

	public void setUserDailyTaskListDaoMysql(
			UserDailyTaskListDaoMysql userDailyTaskListDaoMysql) {
		this.userDailyTaskListDaoMysql = userDailyTaskListDaoMysql;
	}

	@Override
	protected Map<String, UserDailyTaskList> loadFromDb(String userId) {
		Map<String, UserDailyTaskList> map = Maps.newConcurrentMap();
		List<UserDailyTaskList> list = this.userDailyTaskListDaoMysql.getUserDailyTaskList(userId);
		for (UserDailyTaskList taskList : list) {
			map.put(taskList.getSystemTaskId() + "", taskList);
		}
		
		return map;
	}

	@Override
	public List<UserDailyTaskList> getUserDailyTaskList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean addList(String userId, List<UserDailyTaskList> list) {
		if (userDailyTaskListDaoMysql.addList(userId, list)) {
			if (super.isExitKey(userId)) {
				for (UserDailyTaskList userDailyTaskList : list) {
					super.addMapValues(userId, userDailyTaskList.getSystemTaskId() + "", userDailyTaskList);
				}
			}
		
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserDailyTaskList(String userId) {
		if (userDailyTaskListDaoMysql.deleteUserDailyTaskList(userId)) {
			if (super.isExitKey(userId)) {
				super.delete(userId);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserDailyTaskList(String userId, int systemTaskId) {
		if (userDailyTaskListDaoMysql.deleteUserDailyTaskList(userId, systemTaskId)) {
			if (super.isExitKey(userId)) {
				for (UserDailyTaskList task : super.getMapList(userId)) {
					if (task.getSystemTaskId() != systemTaskId)
						super.deleteKey(userId, task.getSystemTaskId() + "");
				}
			}			
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserDailyTaskList(String userId, int systemTaskId, int fiveStar) {
		if (this.userDailyTaskListDaoMysql.updateUserDailyTaskList(userId, systemTaskId, fiveStar)) {
			if (super.isExitKey(userId)) {
				UserDailyTaskList userDailyTaskList = super.getByKey(userId, systemTaskId + "");
				userDailyTaskList.setStar(fiveStar);
				userDailyTaskList.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserDailyTaskList getUserDailyTaskList(String userId,int systemTaskId) {
		return super.getByKey(userId, systemTaskId + "");
	}
	
}
