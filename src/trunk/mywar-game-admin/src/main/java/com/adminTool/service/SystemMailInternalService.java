package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemMailInternal;
import com.adminTool.dao.SystemMailInternalDao;
import com.framework.common.DBSource;

public class SystemMailInternalService {

	private SystemMailInternalDao systemMailInternalDao;

	public SystemMailInternalDao getSystemMailInternalDao() {
		return systemMailInternalDao;
	}

	public void setSystemMailInternalDao(SystemMailInternalDao systemMailInternalDao) {
		this.systemMailInternalDao = systemMailInternalDao;
	}
	
	public List<SystemMailInternal> findAll() {
		systemMailInternalDao.closeSession(DBSource.CFG);
		return systemMailInternalDao.findAll();
	}
	
	public List<SystemMailInternal> findByMailId(Integer mailId) {
		return systemMailInternalDao.find("from SystemMailInternal where internalMailId = " + mailId, DBSource.CFG);
	}
	
	public void update(SystemMailInternal mail) {
		systemMailInternalDao.closeSession(DBSource.CFG);
		systemMailInternalDao.saveOrUpdate(mail);
	}
}
