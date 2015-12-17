package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemConfigData;
import com.adminTool.dao.SystemConfigDataDao;
import com.framework.common.DBSource;

public class SystemConfigDataService {

	private SystemConfigDataDao systemConfigDataDao;

	/**
	 * 查找所有数据
	 * @return
	 */
	public List<SystemConfigData> getList() {
		return systemConfigDataDao.find("FROM SystemConfigData",
						DBSource.CFG);
	}
	
	public SystemConfigData findByConfigDataId(Integer configDataId) {
		return systemConfigDataDao.loadBy("configDataId", configDataId,
				DBSource.CFG);
	}
	
	public void update(SystemConfigData s) {
		systemConfigDataDao.update(s, DBSource.CFG);
	}
	
	public SystemConfigDataDao getSystemConfigDataDao() {
		return systemConfigDataDao;
	}

	public void setSystemConfigDataDao(SystemConfigDataDao systemConfigDataDao) {
		this.systemConfigDataDao = systemConfigDataDao;
	}
	
}
