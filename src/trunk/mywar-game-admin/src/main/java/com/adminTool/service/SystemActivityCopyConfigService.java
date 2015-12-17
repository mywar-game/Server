package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemActivityCopyConfig;
import com.adminTool.dao.SystemActivityCopyConfigDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemActivityCopyConfigService {

	private SystemActivityCopyConfigDao systemActivityCopyConfigDao;

	public List<SystemActivityCopyConfig> findAll(Integer activityId) {
		/*systemActivityCopyConfigDao.closeSession(DBSource.CFG);*/
		return systemActivityCopyConfigDao.find("from SystemActivityCopyConfig where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("活动副本条件配置 同步删除-system_activity_copy_config开始");
		systemActivityCopyConfigDao.closeSession(DBSource.CFG);
		systemActivityCopyConfigDao.executeSQL_("delete from system_activity_copy_config where activity_id = " + activityId);
		LogSystem.info("活动副本条件配置 同步删除-system_activity_copy_config结束");
	}
	
	public void synSaveAll(List<SystemActivityCopyConfig> list) {
		LogSystem.info("活动副本条件配置 同步保存-system_activity_copy_config开始");
		systemActivityCopyConfigDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("活动副本条件配置 同步保存-system_activity_copy_config结束");
	}
	
	public SystemActivityCopyConfigDao getSystemActivityCopyConfigDao() {
		return systemActivityCopyConfigDao;
	}

	public void setSystemActivityCopyConfigDao(
			SystemActivityCopyConfigDao systemActivityCopyConfigDao) {
		this.systemActivityCopyConfigDao = systemActivityCopyConfigDao;
	}
}
