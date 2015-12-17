package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemActivityCopyConditionDrop;
import com.adminTool.dao.SystemActivityCopyConditionDropDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemActivityCopyConditionDropService {

	private SystemActivityCopyConditionDropDao systemActivityCopyConditionDropDao;

	public List<SystemActivityCopyConditionDrop> findAll(Integer activityId) {
		/*systemActivityCopyConditionDropDao.closeSession(DBSource.CFG);*/
		return systemActivityCopyConditionDropDao.find("from SystemActivityCopyConditionDrop where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("此地无银 金角银角 英雄经验 同步删除-system_activity_copy_condition_drop开始");
		systemActivityCopyConditionDropDao.closeSession(DBSource.CFG);
		systemActivityCopyConditionDropDao.executeSQL_("delete from system_activity_copy_condition_drop where activity_id = " + activityId);
		LogSystem.info("此地无银 金角银角 英雄经验 同步删除-system_activity_copy_condition_drop结束");
	}
	
	public void synSaveAll(List<SystemActivityCopyConditionDrop> list) {
		LogSystem.info("此地无银 金角银角 英雄经验 同步保存-system_activity_copy_condition_drop开始");
		systemActivityCopyConditionDropDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("此地无银 金角银角 英雄经验 同步保存-system_activity_copy_condition_drop结束");
	}
	
	public SystemActivityCopyConditionDropDao getSystemActivityCopyConditionDropDao() {
		return systemActivityCopyConditionDropDao;
	}

	public void setSystemActivityCopyConditionDropDao(
			SystemActivityCopyConditionDropDao systemActivityCopyConditionDropDao) {
		this.systemActivityCopyConditionDropDao = systemActivityCopyConditionDropDao;
	}
}
