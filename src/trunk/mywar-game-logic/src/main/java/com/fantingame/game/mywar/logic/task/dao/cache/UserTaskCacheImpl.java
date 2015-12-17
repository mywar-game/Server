package com.fantingame.game.mywar.logic.task.dao.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.task.dao.UserTaskDao;
import com.fantingame.game.mywar.logic.task.dao.mysql.UserTaskDaoMysqlImpl;
import com.fantingame.game.mywar.logic.task.model.UserTask;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserTaskCacheImpl extends BaseCacheMapDao<UserTask> implements UserTaskDao {

	private UserTaskDaoMysqlImpl userTaskDaoMysqlImpl;
	
	@Override
	public List<UserTask> getList(String userId, int status) {
		if (status==100) {
			return super.getMapList(userId);
		} else {
			List<UserTask> result = Lists.newArrayList();
			Collection<UserTask> list = super.getMapValues(userId);
			for(UserTask userTask:list){
				if(userTask.getStatus() == status){
					result.add(userTask);
				}
			}
			return result;
		}
	}

	@Override
	public boolean update(String userId, int systemTaskId, int finishTimes,
			int status) {
		if(userTaskDaoMysqlImpl.update(userId, systemTaskId, finishTimes, status)){
			if(super.isExitKey(userId)){
				UserTask userTask = super.getByKey(userId, systemTaskId+"");
				if(userTask!=null){
					userTask.setFinishTimes(finishTimes);
					userTask.setStatus(status);
					userTask.setUpdatedTime(new Date());
				}else{
					LogSystem.warn("找不到用户任务记录,userId="+userId+",systemTaskId="+systemTaskId);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean update(String userId, int systemTaskId, int status) {
		if(userTaskDaoMysqlImpl.update(userId, systemTaskId,status)){
			if(super.isExitKey(userId)){
				UserTask userTask = super.getByKey(userId, systemTaskId+"");
				if(userTask!=null){
					userTask.setStatus(status);
					userTask.setUpdatedTime(new Date());
				}else{
					LogSystem.warn("找不到用户任务记录,userId="+userId+",systemTaskId="+systemTaskId);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void add(List<UserTask> userTaskList) {
		 userTaskDaoMysqlImpl.add(userTaskList);
		 if(super.isExitKey(userTaskList.get(0).getUserId())){
			 for(UserTask userTask:userTaskList){
				 super.addMapValues(userTask.getUserId(), userTask.getSystemTaskId() + "", userTask);
			 }
		 }
	}
	
	@Override
	public boolean addUserTask(UserTask userTask) {
		if (userTaskDaoMysqlImpl.addUserTask(userTask)) {
			if (super.isExitKey(userTask.getUserId())) {
				super.addMapValues(userTask.getUserId(), userTask.getSystemTaskId() + "", userTask);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserTask get(String userId, int systemTaskId) {
		return super.getByKey(userId, systemTaskId+"");
	}
	
	@Override
	public boolean delete(String userId, int systemTaskId) {
		if(userTaskDaoMysqlImpl.delete(userId, systemTaskId)){
			if(super.isExitKey(userId)){
				super.deleteKey(userId, systemTaskId+"");
			}
			return true;
		}
		return false;
	}
	
	public UserTaskDaoMysqlImpl getUserTaskDaoMysqlImpl() {
		return userTaskDaoMysqlImpl;
	}
	
	public void setUserTaskDaoMysqlImpl(UserTaskDaoMysqlImpl userTaskDaoMysqlImpl) {
		this.userTaskDaoMysqlImpl = userTaskDaoMysqlImpl;
	}
	
	@Override
	protected Map<String, UserTask> loadFromDb(String key) {
		List<UserTask> userTasks = userTaskDaoMysqlImpl.getList(key, 100);
		Map<String,UserTask> map = Maps.newConcurrentMap();
		for(UserTask task:userTasks){
			map.put(task.getSystemTaskId()+"", task);
		}
		return map;
	}
}
