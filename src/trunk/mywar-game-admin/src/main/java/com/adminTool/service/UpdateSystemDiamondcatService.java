package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemDiamondcat;
import com.adminTool.dao.SystemDiamondcatDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class UpdateSystemDiamondcatService {

	private SystemDiamondcatDao systemDiamondcatDao;

	public List<SystemDiamondcat> getList(int activityId) {
		return systemDiamondcatDao.find("from SystemDiamondcat where activityId = " + activityId, DBSource.CFG);
	}
	
	public void deleteList(int activityId) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from system_diamondcat where activity_id = ");
		sb.append(activityId);
		systemDiamondcatDao.closeSession(DBSource.CFG);
		systemDiamondcatDao.executeSQL_(sb.toString());
	}
	
	public void saveList(List<SystemDiamondcat> list) {
		systemDiamondcatDao.saveBatch(list, DBSource.CFG);
	}
	
	public SystemDiamondcatDao getSystemDiamondcatDao() {
		return systemDiamondcatDao;
	}

	public void setSystemDiamondcatDao(SystemDiamondcatDao systemDiamondcatDao) {
		this.systemDiamondcatDao = systemDiamondcatDao;
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("招财猫模块 同步删除-system_diamondcat开始");
		systemDiamondcatDao.closeSession(DBSource.CFG);
		systemDiamondcatDao.executeSQL_("delete from system_diamondcat where activity_id = " + activityId);
		LogSystem.info("招财猫模块 同步删除-system_diamondcat结束");
	}
	
	public void synSaveAll(List<SystemDiamondcat> list) {
		LogSystem.info("招财猫模块 同步保存-system_diamondcat开始");
		systemDiamondcatDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("招财猫模块 同步保存-system_diamondcat结束");
	}
}
