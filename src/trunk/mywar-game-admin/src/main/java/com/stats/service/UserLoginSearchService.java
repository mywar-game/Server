package com.stats.service;

import java.util.List;

import com.framework.common.DBSource;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.dao.UserLoginSearchDao;

/**
 * 玩家登入实时查询
 * @author Administrator
 *
 */
public class UserLoginSearchService {

	private UserLoginSearchDao userLoginSearchDao;

	public UserEverydayLoginStats find(String beginDate, String enDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1),COUNT(DISTINCT(USER_ID)),COUNT(DISTINCT(IP)) FROM (select * from user_login_log union all select * from user_login_log_bak) log WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(beginDate);
		sb.append("' AND '");
		sb.append(enDate);
		sb.append("'");

		userLoginSearchDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginSearchDao.findSQL_(sb.toString());
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
	
	public UserLoginSearchDao getUserLoginSearchDao() {
		return userLoginSearchDao;
	}

	public void setUserLoginSearchDao(UserLoginSearchDao userLoginSearchDao) {
		this.userLoginSearchDao = userLoginSearchDao;
	}
	
}
