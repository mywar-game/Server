package com.log.service;

import java.util.List;

import com.framework.common.DBSource;
import com.log.dao.UserRefreshHeroLogDao;
import com.stats.bo.UserRefreshHeroStats;

/**
 * @author hzy
 * 2012-7-20
 */
public class UserRefreshHeroLogService {
	
	/**
	 * 
	 */
	private UserRefreshHeroLogDao userRefreshHeroLogDao;
	
	/**
	 * @param dates 
	 * @param userRefreshHeroStats 
	 */
	public void collectUserRefreshHeroStatsDataInSomeDay(String[] dates, UserRefreshHeroStats userRefreshHeroStats) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT TYPE, COUNT(USER_REFRESH_HERO_LOG_ID), SUM(COMMON_QUALITY_NUM), SUM(EXCELLENT_QUALITY_NUM), SUM(ELITE_QUALITY_NUM), SUM(SUPER_QUALITY_NUM) FROM user_refresh_hero_log WHERE REFRESH_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("' GROUP BY TYPE ");
		
		userRefreshHeroLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRefreshHeroLogDao.findSQL_(sb.toString());
//		if (list == null  || list.size() == 0) {
//			userRefreshHeroStats = null;
//			return;
//		}
		Integer refreshTimes = 0;
		Integer chargeTimes = 0;
		Integer commonQualityNum = 0;
		Integer excellentQualityNum = 0;
		Integer eliteQualityNum = 0;
		Integer superQualityNum = 0;
		for (int i = 0; i < list.size(); i++) {
			int index = 0;
			Integer type = Integer.valueOf(((Object[]) list.get(i))[index].toString());
			
			refreshTimes += Integer.valueOf(((Object[]) list.get(i))[++index].toString());
			//类型（0免费1收费）
			if (type == 1) {
				chargeTimes = Integer.valueOf(((Object[]) list.get(i))[index].toString());
			}
			commonQualityNum += Integer.valueOf(((Object[]) list.get(i))[++index].toString());
			excellentQualityNum += Integer.valueOf(((Object[]) list.get(i))[++index].toString());
			eliteQualityNum += Integer.valueOf(((Object[]) list.get(i))[++index].toString());
			superQualityNum += Integer.valueOf(((Object[]) list.get(i))[++index].toString());
		}
		userRefreshHeroStats.setRefreshTimes(refreshTimes);
		userRefreshHeroStats.setChargeTimes(chargeTimes);
		userRefreshHeroStats.setCommonQualityNum(commonQualityNum);
		userRefreshHeroStats.setExcellentQualityNum(excellentQualityNum);
		userRefreshHeroStats.setEliteQualityNum(eliteQualityNum);
		userRefreshHeroStats.setSuperQualityNum(superQualityNum);
	}

	/**
	 * @return the userRefreshHeroLogDao
	 */
	public UserRefreshHeroLogDao getUserRefreshHeroLogDao() {
		return userRefreshHeroLogDao;
	}

	/**
	 * @param userRefreshHeroLogDao the userRefreshHeroLogDao to set
	 */
	public void setUserRefreshHeroLogDao(UserRefreshHeroLogDao userRefreshHeroLogDao) {
		this.userRefreshHeroLogDao = userRefreshHeroLogDao;
	}
}
