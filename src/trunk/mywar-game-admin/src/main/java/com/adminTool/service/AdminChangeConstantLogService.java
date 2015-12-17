package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.dao.AdminChangeConstantLogDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;

public class AdminChangeConstantLogService {

	private AdminChangeConstantLogDao adminChangeConstantLogDao;
	
	public void saveOne(AdminChangeConstantLog adminChangeConstantLog){
		adminChangeConstantLogDao.save(adminChangeConstantLog, DBSource.ADMIN);
	}

	public IPage<AdminChangeConstantLog> findPageList(Integer pageSize, Integer pageIndex) {
		adminChangeConstantLogDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(0);
		return adminChangeConstantLogDao.findPage("from AdminChangeConstantLog where sysNum = ? or sysNum = ?", list, pageSize, pageIndex);
	}
	
	public void setAdminChangeConstantLogDao(AdminChangeConstantLogDao adminChangeConstantLogDao) {
		this.adminChangeConstantLogDao = adminChangeConstantLogDao;
	}

	public AdminChangeConstantLogDao getAdminChangeConstantLogDao() {
		return adminChangeConstantLogDao;
	}
}
