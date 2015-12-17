package com.stats.service;

import java.util.List;

import com.framework.common.DBSource;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.dao.UserEveryMonthLoginStatsDao;

public class UserEveryMonthLoginStatsService {

	private UserEveryMonthLoginStatsDao userEveryMonthLoginStatsDao;

	public UserEverydayLoginStats find(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1),COUNT(DISTINCT(USER_ID)),COUNT(DISTINCT(IP)) FROM (select * from user_login_log union all select * from user_login_log_bak) log WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");

		userEveryMonthLoginStatsDao.closeSession(DBSource.LOG);
		List<Object> list = userEveryMonthLoginStatsDao.findSQL_(sb.toString());
		UserEverydayLoginStats userEverydayLoginStats = new UserEverydayLoginStats();
		if (list != null && list.size() > 0) {
			userEverydayLoginStats.setTotalTimes(Integer
					.parseInt(((Object[]) list.get(0))[0].toString()));
			userEverydayLoginStats.setUserNumber(Integer
					.parseInt(((Object[]) list.get(0))[1].toString()));
			userEverydayLoginStats.setIpNumber(Integer
					.parseInt(((Object[]) list.get(0))[2].toString()));
		}
		return userEverydayLoginStats;
	}
	/**
	 * 查找最大，最小日期
	 */
	public List<Object> findLastAndLeastTime() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(LOGIN_TIME), MIN(LOGIN_TIME) FROM (select * from user_login_log union all select * from user_login_log_bak) log");
		userEveryMonthLoginStatsDao.closeSession(DBSource.LOG);
		List<Object> list = userEveryMonthLoginStatsDao.findSQL_(sb.toString());
		return list;
	}
	
	
	public UserEveryMonthLoginStatsDao getUserEveryMonthLoginStatsDao() {
		return userEveryMonthLoginStatsDao;
	}

	public void setUserEveryMonthLoginStatsDao(
			UserEveryMonthLoginStatsDao userEveryMonthLoginStatsDao) {
		this.userEveryMonthLoginStatsDao = userEveryMonthLoginStatsDao;
	}
	
}
