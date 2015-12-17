package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemActivityScratchReward;
import com.adminTool.dao.SystemActivityScratchRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

/**
 * 刮刮乐
 * @author Administrator
 *
 */
public class SystemActivityScratchRewardService {

	private SystemActivityScratchRewardDao systemActivityScratchRewardDao;

	public List<SystemActivityScratchReward> findAll(int activityId) {
//		systemActivityScratchRewardDao.closeSession(DBSource.CFG);
//		return systemActivityScratchRewardDao.findAll();
		return systemActivityScratchRewardDao.find("from SystemActivityScratchReward where activityId = " + activityId, DBSource.CFG);
	}
	
	public void update(SystemActivityScratchReward reward) {
		systemActivityScratchRewardDao.update(reward, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(int activityId) {
		LogSystem.info("热血刮刮乐 同步删除-system_activity_scratch_reward开始");
		systemActivityScratchRewardDao.closeSession(DBSource.CFG);
		systemActivityScratchRewardDao.executeSQL_("delete from system_activity_scratch_reward where activity_id = " + activityId);
		LogSystem.info("热血刮刮乐 同步删除-system_activity_scratch_reward结束");
	}
	
	public void synSaveAll(List<SystemActivityScratchReward> list) {
		LogSystem.info("热血刮刮乐 同步保存-system_activity_scratch_reward开始");
		systemActivityScratchRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("热血刮刮乐 同步保存-system_activity_scratch_reward结束");
	}
	
	
	public SystemActivityScratchRewardDao getSystemActivityScratchRewardDao() {
		return systemActivityScratchRewardDao;
	}

	public void setSystemActivityScratchRewardDao(
			SystemActivityScratchRewardDao systemActivityScratchRewardDao) {
		this.systemActivityScratchRewardDao = systemActivityScratchRewardDao;
	}
}
