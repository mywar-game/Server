package com.stats.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserLevelIntervalConsumeTypeStats;
import com.stats.dao.UserLevelIntervalConsumeTypeStatsDao;

public class UserLevelIntervalConsumeTypeStatsService {
	
	private UserLevelIntervalConsumeTypeStatsDao userLevelIntervalConsumeTypeStatsDao;
	
	public void save(UserLevelIntervalConsumeTypeStats userLevelIntervalConsumeTypeStats){
		userLevelIntervalConsumeTypeStatsDao.save(userLevelIntervalConsumeTypeStats, DBSource.ADMIN);
	}

	public void find(Map<String, Integer> statsMap, Map<String, Integer> testStatsMap){
		userLevelIntervalConsumeTypeStatsDao.closeSession(DBSource.ADMIN);
		List<UserLevelIntervalConsumeTypeStats> list = userLevelIntervalConsumeTypeStatsDao.find("from UserLevelIntervalConsumeTypeStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>());
		//消费信息：2-11_10,5-4_100 格式是 等级区间索引-消费类型_消费金额,。。。
		if (list != null && list.size() > 0) {
			String[] consumeInfoArr;
			//统计map的key： 等级区间索引-消费类型
			String key;
			//key对应的消费金额
			Integer consumeAmount;
			for (UserLevelIntervalConsumeTypeStats userLevelIntervalConsumeTypeStats : list) {
				if ("".equals(userLevelIntervalConsumeTypeStats.getConsumeInfo())) {
					continue;
				}
				consumeInfoArr = userLevelIntervalConsumeTypeStats.getConsumeInfo().split(",");
				for (String oneInfo : consumeInfoArr) {
					String[] arr = oneInfo.split("_");
					key = arr[0];
					consumeAmount = Integer.parseInt(arr[1]);
					if (statsMap.get(key) != null) {
						statsMap.put(key, consumeAmount + statsMap.get(key));
					} else {
						statsMap.put(key, consumeAmount);
					}
				}
			}
			//处理测试号
//			for (UserLevelIntervalConsumeTypeStats userLevelIntervalConsumeTypeStats : list) {
//				if ("".equals(userLevelIntervalConsumeTypeStats.getTestUserConsumeInfo())) {
//					continue;
//				}
//				consumeInfoArr = userLevelIntervalConsumeTypeStats.getTestUserConsumeInfo().split(",");
//				for (String oneInfo : consumeInfoArr) {
//					key = oneInfo.split("_")[0];
//					consumeAmount = Integer.parseInt(oneInfo.split("_")[1]);
//					if (testStatsMap.get(key) != null) {
//						testStatsMap.put(key, consumeAmount + testStatsMap.get(key));
//					} else {
//						testStatsMap.put(key, consumeAmount);
//					}
//				}
//			}
		}
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserLevelIntervalConsumeTypeStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		userLevelIntervalConsumeTypeStatsDao.closeSession(DBSource.ADMIN);
		userLevelIntervalConsumeTypeStatsDao.execute(sql.toString());
	}
	
	public void setUserLevelIntervalConsumeTypeStatsDao(UserLevelIntervalConsumeTypeStatsDao userLevelIntervalConsumeTypeStatsDao) {
		this.userLevelIntervalConsumeTypeStatsDao = userLevelIntervalConsumeTypeStatsDao;
	}

	public UserLevelIntervalConsumeTypeStatsDao getUserLevelIntervalConsumeTypeStatsDao() {
		return userLevelIntervalConsumeTypeStatsDao;
	}

}
