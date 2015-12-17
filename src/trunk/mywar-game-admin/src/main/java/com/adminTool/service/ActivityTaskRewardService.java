package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.ActivityTaskReward;
import com.adminTool.dao.ActivityTaskRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

/**
 * 活跃度奖励
 * @author Administrator
 *
 */
public class ActivityTaskRewardService {

	private ActivityTaskRewardDao activityTaskRewardDao;
	
	public List<ActivityTaskReward> findAll() {
		activityTaskRewardDao.closeSession(DBSource.CFG);
		return activityTaskRewardDao.findAll();
	}
	
	public void save(ActivityTaskReward reward) {
		activityTaskRewardDao.save(reward, DBSource.CFG);
	}
	
	public void deleteAll() {
		activityTaskRewardDao.closeSession(DBSource.CFG);
		activityTaskRewardDao.execute("delete ActivityTaskReward where 1=1");
	}
	
	public void deleteByTaskRewardId(Integer id) {
		activityTaskRewardDao.closeSession(DBSource.CFG);
		activityTaskRewardDao.execute("delete ActivityTaskReward where activityTaskRewardId=" + id);
	}
	
	public ActivityTaskRewardDao getActivityTaskRewardDao() {
		return activityTaskRewardDao;
	}

	public void setActivityTaskRewardDao(ActivityTaskRewardDao activityTaskRewardDao) {
		this.activityTaskRewardDao = activityTaskRewardDao;
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("活跃度 同步删除-system_activity_task_reward开始");
		activityTaskRewardDao.closeSession(DBSource.CFG);
		activityTaskRewardDao.executeSQL_("delete from system_activity_task_reward where 1=1");
		LogSystem.info("活跃度 同步删除-system_activity_task_reward结束");
	}
	
	public void synSaveAll(List<ActivityTaskReward> list) {
		LogSystem.info("活跃度 同步保存-system_activity_task_reward开始");
		activityTaskRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("活跃度 同步保存-system_activity_task_reward结束");
	}

}
