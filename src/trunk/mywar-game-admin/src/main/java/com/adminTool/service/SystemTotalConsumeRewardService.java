package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemTotalConsumeReward;
import com.adminTool.dao.SystemTotalConsumeRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemTotalConsumeRewardService {

	private SystemTotalConsumeRewardDao systemTotalConsumeRewardDao;

	
	public List<SystemTotalConsumeReward> findAll(int activityId) {
		return systemTotalConsumeRewardDao.find("from SystemTotalConsumeReward where activityId = " + activityId, DBSource.CFG);
	}
	
	public void update(SystemTotalConsumeReward reward) {
		systemTotalConsumeRewardDao.update(reward, DBSource.CFG);
	}
	
	public void deleteAll(int activityId) {
		systemTotalConsumeRewardDao.closeSession(DBSource.CFG);
		systemTotalConsumeRewardDao.executeSQL_("delete from system_total_consume_reward where activity_id = " + activityId);
	}
	
	public void deleteById(Integer id) {
		systemTotalConsumeRewardDao.closeSession(DBSource.CFG);
		systemTotalConsumeRewardDao.executeSQL_("delete from system_total_consume_reward where id=" + id);
	}
	
	public void save(SystemTotalConsumeReward reward) {
		systemTotalConsumeRewardDao.save(reward, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(int activityId) {
		LogSystem.info("累计消费 同步删除-system_total_consume_reward开始");
		systemTotalConsumeRewardDao.closeSession(DBSource.CFG);
		systemTotalConsumeRewardDao.executeSQL_("delete from system_total_consume_reward where activity_id = " + activityId);
		LogSystem.info("累计消费 同步删除-system_total_consume_reward结束");
	}
	
	public void synSaveAll(List<SystemTotalConsumeReward> list) {
		LogSystem.info("累计消费 同步保存-system_total_consume_reward开始");
		systemTotalConsumeRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("累计消费 同步保存-system_total_consume_reward结束");
	}
	
	public SystemTotalConsumeRewardDao getSystemTotalConsumeRewardDao() {
		return systemTotalConsumeRewardDao;
	}

	public void setSystemTotalConsumeRewardDao(
			SystemTotalConsumeRewardDao systemTotalConsumeRewardDao) {
		this.systemTotalConsumeRewardDao = systemTotalConsumeRewardDao;
	}
	
	
}
