package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemReceivePower;
import com.adminTool.dao.SystemReceivePowerDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemReceivePowerService {

	private SystemReceivePowerDao systemReceivePowerDao;

	public SystemReceivePowerDao getSystemReceivePowerDao() {
		return systemReceivePowerDao;
	}

	public void setSystemReceivePowerDao(SystemReceivePowerDao systemReceivePowerDao) {
		this.systemReceivePowerDao = systemReceivePowerDao;
	}

	public List<SystemReceivePower> findAll() {
		systemReceivePowerDao.closeSession(DBSource.CFG);
		return systemReceivePowerDao.findAll();
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("道具兑换 同步删除-system_recive_power开始");
		systemReceivePowerDao.closeSession(DBSource.CFG);
		systemReceivePowerDao.executeSQL_("delete from system_recive_power where 1=1");
		LogSystem.info("道具兑换 同步删除-system_recive_power结束");
	}
	
	public void synSaveAll(List<SystemReceivePower> list) {
		LogSystem.info("体力恢复 同步保存-system_recive_power开始");
		systemReceivePowerDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("体力恢复 同步保存-system_recive_power结束");
	}
}
