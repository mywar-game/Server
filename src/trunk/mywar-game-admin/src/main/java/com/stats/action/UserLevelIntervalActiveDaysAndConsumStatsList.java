package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserGoldLogService;
import com.log.service.UserLoginLogService;

/**
 * 玩家每等级区间的各活跃天数区间的人数和消费统计
 * @author hzy
 * 2012-8-2
 */
public class UserLevelIntervalActiveDaysAndConsumStatsList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	
	/** 所有玩家 */
	private Map<String, String> statsMap = new HashMap<String, String>();
	
	/** 测试号 */
	private Map<String, String> testStatsMap = new HashMap<String, String>();
	
	private int[][] activeDaysInterval = {
			{4,2}, {9,5}, {15,9}, {20,12}, {21,13}, {60,10}, {60,10}, {60,10}, {60,10}, {60,10},
			{60,10}, {6,1}, {60,10}, {60,10}, {60,10}, {60,10}, {60,10}, {60,10}, {60,10}, {60,10},
			{60,10},{60,10},{60,10},{60,10}
	};
	
	@Override
	public String execute() throws Exception {
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		

		//{4,2} 等级为0-5区间时，活跃天数大于或等于4的为小户， 活跃天数大于或等于2小于4为中户，活跃天数小于2的为大户。
		
		List<Object> activeDaysList = userLoginLogService.findUserIdAndLevelIntervalIndexAndActiveDaysList();
		
		Map<String,Map<Integer,Integer>> userIdAndLevelIntervalIndexAndConsumeNumMap = userGoldLogService.findUserIdAndLevelIntervalIndexAndConsumeNumMap();
		
		Integer levelIntervalIndex;
		String userId;
		Integer activeDays;
		//0小户 1中户 2大户
		Integer userConsumeType;
		String key;
		Integer consumeNum;
		if (activeDaysList != null && activeDaysList.size() > 0) {
			for (int i = 0; i < activeDaysList.size(); i++) {
				userId = ((Object[]) activeDaysList.get(i))[0].toString();
				levelIntervalIndex = Integer.valueOf(((Object[]) activeDaysList.get(i))[1].toString());
				activeDays = Integer.valueOf(((Object[]) activeDaysList.get(i))[2].toString());
				
				if (userIdAndLevelIntervalIndexAndConsumeNumMap.get(userId) != null && userIdAndLevelIntervalIndexAndConsumeNumMap.get(userId).get(levelIntervalIndex) != null) {
					consumeNum = userIdAndLevelIntervalIndexAndConsumeNumMap.get(userId).get(levelIntervalIndex);
				} else {
					consumeNum = 0;
				}
				
				if (activeDays >= activeDaysInterval[levelIntervalIndex][0]) {
					userConsumeType = 0;
				} else if (activeDays >= activeDaysInterval[levelIntervalIndex][1]) {
					userConsumeType = 1;
				} else {
					userConsumeType = 2;
				}
				key = levelIntervalIndex + "-" + userConsumeType;
				if (statsMap.get(key) != null) {
					statsMap.put(key, (Integer.valueOf(statsMap.get(key).split("-")[0])+1) + "-" + (Integer.valueOf(statsMap.get(key).split("-")[1])+consumeNum));
				} else {
					statsMap.put(key, "1-" + consumeNum);
				}
//				System.out.println(statsMap);
//				System.out.println();
			}
		}
		
		
		
		return SUCCESS;
	}

	/**
	 * @return the statsMap
	 */
	public Map<String, String> getStatsMap() {
		return statsMap;
	}

	/**
	 * @param statsMap the statsMap to set
	 */
	public void setStatsMap(Map<String, String> statsMap) {
		this.statsMap = statsMap;
	}

	/**
	 * @return the testStatsMap
	 */
	public Map<String, String> getTestStatsMap() {
		return testStatsMap;
	}

	/**
	 * @param testStatsMap the testStatsMap to set
	 */
	public void setTestStatsMap(Map<String, String> testStatsMap) {
		this.testStatsMap = testStatsMap;
	}

	public void setActiveDaysInterval(int[][] activeDaysInterval) {
		this.activeDaysInterval = activeDaysInterval;
	}

	public int[][] getActiveDaysInterval() {
		return activeDaysInterval;
	}
}
