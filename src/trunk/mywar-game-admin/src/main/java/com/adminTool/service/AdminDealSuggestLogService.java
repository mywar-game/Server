package com.adminTool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminDealSuggestLog;
import com.adminTool.dao.AdminDealSuggestLogDao;
import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;

public class AdminDealSuggestLogService {

	private AdminDealSuggestLogDao adminDealSuggestLogDao;
	
	/**
	 * 根据建议id查找对建议的处理
	 * @param suggestIds
	 * @return
	 */
	public Map<Integer, AdminDealSuggestLog> findUserIdNameMapBySuggestIds(String suggestIds) {
		Map<Integer, AdminDealSuggestLog> suggestIdAndDAdminDealMap = new HashMap<Integer, AdminDealSuggestLog>();
		List<AdminDealSuggestLog> list = adminDealSuggestLogDao.find("from AdminDealSuggestLog where suggestId in (" + suggestIds + ") and sysNum = " + CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
		for (int i = 0; i < list.size(); i++) {
			AdminDealSuggestLog log = list.get(i);
			suggestIdAndDAdminDealMap.put(log.getSuggestId(), log);
		}
		return suggestIdAndDAdminDealMap;
	}

	public void save(AdminDealSuggestLog adminDealSuggestLog) {
		adminDealSuggestLogDao.save(adminDealSuggestLog, DBSource.ADMIN);
	}
	
	public void setAdminDealSuggestLogDao(AdminDealSuggestLogDao adminDealSuggestLogDao) {
		this.adminDealSuggestLogDao = adminDealSuggestLogDao;
	}

	public AdminDealSuggestLogDao getAdminDealSuggestLogDao() {
		return adminDealSuggestLogDao;
	}
}
