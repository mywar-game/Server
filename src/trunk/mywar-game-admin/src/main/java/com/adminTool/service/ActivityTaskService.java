package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.ActivityTask;
import com.adminTool.dao.ActivityTaskDao;
import com.framework.common.DBSource;

/**
 * 活跃度
 * @author Administrator
 *
 */
public class ActivityTaskService {

	private ActivityTaskDao activityTaskDao;
	
	public List<ActivityTask> findAll() {
		activityTaskDao.closeSession(DBSource.CFG);
		return activityTaskDao.findAll();
	}
	
	public void save(ActivityTask task) {
		activityTaskDao.save(task, DBSource.CFG);
	}
	
	public ActivityTask findLastActivity() {
		// activityTaskDao.closeSession(DBSource.CFG);
		List<ActivityTask> list = activityTaskDao
			.find("FROM ActivityTask ORDER BY activityTaskId DESC LIMIT 1 ",
					DBSource.CFG);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	public void deleteActivityTask() {
		activityTaskDao.closeSession(DBSource.CFG);
		activityTaskDao.execute("delete ActivityTask where 1=1");
	}

	public ActivityTaskDao getActivityTaskDao() {
		return activityTaskDao;
	}

	public void setActivityTaskDao(ActivityTaskDao activityTaskDao) {
		this.activityTaskDao = activityTaskDao;
	}

}
