package com.adminTool.service;

import java.util.ArrayList;

import com.adminTool.bo.AdminIssueLog;
import com.adminTool.dao.AdminIssueLogDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;

public class AdminIssueLogService {

	private AdminIssueLogDao adminIssueLogDao;

	public void saveAdminIssueLog(AdminIssueLog adminIssueLog) {
		adminIssueLogDao.save(adminIssueLog, DBSource.ADMIN);
	}

	public IPage<AdminIssueLog> findPageList(Integer pageSize, Integer pageIndex) {
		adminIssueLogDao.closeSession(DBSource.ADMIN);
		return adminIssueLogDao.findPage("from AdminIssueLog where sysNum = "
				+ CustomerContextHolder.getSystemNum()
				+ " order by issueTime desc", new ArrayList<Object>(),
				pageSize, pageIndex);
	}

	public void setAdminIssueLogDao(AdminIssueLogDao adminIssueLogDao) {
		this.adminIssueLogDao = adminIssueLogDao;
	}

	public AdminIssueLogDao getAdminIssueLogDao() {
		return adminIssueLogDao;
	}
}
