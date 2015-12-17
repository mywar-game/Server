package com.stats.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserIpStats;
import com.stats.dao.UserIPStatsDao;

public class UserIPStatsService {
	
	private UserIPStatsDao userIPStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userEverydayLoginStats
	 */
	public void saveUserIPStatsList(List<UserIpStats> userIPStatsList) {
		if (userIPStatsList != null && userIPStatsList.size() > 0) {
			for (UserIpStats userIpStats : userIPStatsList) {
				userIPStatsDao.save(userIpStats, DBSource.ADMIN);
			}
		}
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserIpStats> findList(int pageSize, int currentPage) {
		userIPStatsDao.closeSession(DBSource.ADMIN);
		return userIPStatsDao.findPage("from UserIpStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public void deleteBeforeStatsData(){
		userIPStatsDao.closeSession(DBSource.ADMIN);
		String str = "delete from UserIpStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum();
		userIPStatsDao.execute(str);
	}

	public void setUserIPStatsDao(UserIPStatsDao userIPStatsDao) {
		this.userIPStatsDao = userIPStatsDao;
	}

	public UserIPStatsDao getUserIPStatsDao() {
		return userIPStatsDao;
	}

}
