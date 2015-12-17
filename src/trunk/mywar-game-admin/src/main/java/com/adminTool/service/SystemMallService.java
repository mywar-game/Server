package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemMall;
import com.adminTool.dao.SystemMallDao;
import com.framework.common.DBSource;

public class SystemMallService {

	private SystemMallDao systemMallDao;

	public List<SystemMall> getSystemMallList() {
		systemMallDao.closeSession(DBSource.CFG);
		return systemMallDao.findAll();
	}
	
	public SystemMallDao getSystemMallDao() {
		return systemMallDao;
	}

	public void setSystemMallDao(SystemMallDao systemMallDao) {
		this.systemMallDao = systemMallDao;
	}
	
	public List<SystemMall> getSystemMallByMallId(int mallId) {
		return systemMallDao.find("FROM SystemMall where mallId = " + mallId, DBSource.CFG);
	}
	
	public void update(SystemMall systemMall) {
		systemMallDao.update(systemMall, DBSource.CFG);
	}
}
