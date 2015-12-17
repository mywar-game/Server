package com.adminTool.service;

import java.util.ArrayList;

import com.adminTool.bo.AdminIssueDiamondLog;
import com.adminTool.dao.AdminIssueDiamondLogDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;

public class AdminIssueDiamondLogService {

	private AdminIssueDiamondLogDao adminIssueDiamondLogDao;

	public void saveAdminIssueDiamondLog(
			AdminIssueDiamondLog adminIssueDiamondLog) {
		adminIssueDiamondLogDao.save(adminIssueDiamondLog, DBSource.ADMIN);
	}

	public IPage<AdminIssueDiamondLog> findPageList(Integer pageSize,
			Integer pageIndex) {
		adminIssueDiamondLogDao.closeSession(DBSource.ADMIN);
		return adminIssueDiamondLogDao.findPage(
				"from AdminIssueDiamondLog where sysNum = "
						+ CustomerContextHolder.getSystemNum()
						+ " order by issueTime desc", new ArrayList<Object>(),
				pageSize, pageIndex);
	}

	/**
	 * @return the adminIssueDiamondLogDao
	 */
	public AdminIssueDiamondLogDao getAdminIssueDiamondLogDao() {
		return adminIssueDiamondLogDao;
	}

	/**
	 * @param adminIssueDiamondLogDao
	 *            the adminIssueDiamondLogDao to set
	 */
	public void setAdminIssueDiamondLogDao(
			AdminIssueDiamondLogDao adminIssueDiamondLogDao) {
		this.adminIssueDiamondLogDao = adminIssueDiamondLogDao;
	}

}
