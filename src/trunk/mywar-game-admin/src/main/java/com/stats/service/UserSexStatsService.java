package com.stats.service;

import java.util.List;

import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserSexStats;
import com.stats.dao.UserSexStatsDao;

public class UserSexStatsService {
	
	private UserSexStatsDao userSexStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userEverydayLoginStats
	 */
	public void saveUserSexStatsList(List<UserSexStats> userSexStatsList) {
		if (userSexStatsList != null && userSexStatsList.size() > 0) {
			for (UserSexStats userSexStats : userSexStatsList) {
				userSexStatsDao.save(userSexStats, DBSource.ADMIN);
			}
		}
	}
	
	/**
	 * 统计信息列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<UserSexStats> findList() {
		 return userSexStatsDao.find("from UserSexStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
	}
	
	public void deleteBeforeStatsData(){
		userSexStatsDao.closeSession(DBSource.ADMIN);
		String str = "delete from UserSexStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum();
		userSexStatsDao.execute(str);
	}

	public void setUserSexStatsDao(UserSexStatsDao userSexStatsDao) {
		this.userSexStatsDao = userSexStatsDao;
	}

	public UserSexStatsDao getUserSexStatsDao() {
		return userSexStatsDao;
	}

}
