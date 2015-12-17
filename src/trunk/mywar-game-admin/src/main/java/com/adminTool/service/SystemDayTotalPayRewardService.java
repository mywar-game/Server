package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemDayTotalPayReward;
import com.adminTool.dao.SystemDayTotalPayRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemDayTotalPayRewardService {

	private SystemDayTotalPayRewardDao systemDayTotalPayRewardDao;

	public SystemDayTotalPayRewardDao getSystemDayTotalPayRewardDao() {
		return systemDayTotalPayRewardDao;
	}

	public void setSystemDayTotalPayRewardDao(
			SystemDayTotalPayRewardDao systemDayTotalPayRewardDao) {
		this.systemDayTotalPayRewardDao = systemDayTotalPayRewardDao;
	}

	public void insert(SystemDayTotalPayReward s) {
		systemDayTotalPayRewardDao.save(s, DBSource.CFG);
	}
	
	public void update(SystemDayTotalPayReward s) {
		systemDayTotalPayRewardDao.update(s, DBSource.CFG);
	}
	
	public List<SystemDayTotalPayReward> getList(int activityId) {
		return systemDayTotalPayRewardDao.find("from SystemDayTotalPayReward where activityId = " + activityId, DBSource.CFG);
	}
	
	public List<SystemDayTotalPayReward> getById(int activityId, int mallId) {
		List<Object> list = new ArrayList<Object>();
		list.add(activityId);
		list.add(mallId);
		systemDayTotalPayRewardDao.closeSession(DBSource.CFG);
		return systemDayTotalPayRewardDao.find("from SystemDayTotalPayReward where activityId = ? and id = ?", list);
	}
	
	public int getMaxId() {
		systemDayTotalPayRewardDao.closeSession(DBSource.CFG);
		List<SystemDayTotalPayReward> list = systemDayTotalPayRewardDao.find("from SystemDayTotalPayReward order by id desc limit 1", new ArrayList<Object>());
		if (list == null || list.size() == 0) {
			return 0;
		} else {
			return list.get(0).getId();
		}
	}
	
	public List<SystemDayTotalPayReward> findAll(Integer activityId) {
		return systemDayTotalPayRewardDao.find("from SystemDayTotalPayReward where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("当天累积消费模块 同步删除-system_day_total_pay_reward开始");
		systemDayTotalPayRewardDao.closeSession(DBSource.CFG);
		systemDayTotalPayRewardDao.executeSQL_("delete from system_day_total_pay_reward where activity_id = " + activityId);
		LogSystem.info("当天累积消费模块 同步删除-system_day_total_pay_reward结束");
	}
	
	public void synSaveAll(List<SystemDayTotalPayReward> list) {
		LogSystem.info("当天累积消费模块 同步保存-system_day_total_pay_reward开始");
		systemDayTotalPayRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("当天累积消费模块 同步保存-system_day_total_pay_reward结束");
	}
}
