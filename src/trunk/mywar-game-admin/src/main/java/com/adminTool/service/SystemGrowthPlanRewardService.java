package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemGrowthPlanReward;
import com.adminTool.dao.SystemGrowthPlanRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemGrowthPlanRewardService {

	private SystemGrowthPlanRewardDao systemGrowthPlanRewardDao;

	public SystemGrowthPlanRewardDao getSystemGrowthPlanRewardDao() {
		return systemGrowthPlanRewardDao;
	}

	public void setSystemGrowthPlanRewardDao(
			SystemGrowthPlanRewardDao systemGrowthPlanRewardDao) {
		this.systemGrowthPlanRewardDao = systemGrowthPlanRewardDao;
	}
	
	public List<SystemGrowthPlanReward> findAll() {
		systemGrowthPlanRewardDao.closeSession(DBSource.CFG);
		return systemGrowthPlanRewardDao.findAll();
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("坐享其成 同步删除-system_growth_plan_reward开始");
		systemGrowthPlanRewardDao.closeSession(DBSource.CFG);
		systemGrowthPlanRewardDao.executeSQL_("delete from system_growth_plan_reward where 1=1");
		LogSystem.info("坐享其成 同步删除-system_growth_plan_reward结束");
	}
	
	public void synSaveAll(List<SystemGrowthPlanReward> list) {
		LogSystem.info("坐享其成 同步保存-system_growth_plan_reward开始");
		systemGrowthPlanRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("坐享其成 同步保存-system_growth_plan_reward结束");
	}
	
	
}
