package com.stats.action;

import java.util.HashMap;
import java.util.Map;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.service.UserLevelIntervalConsumeTypeStatsService;

/**
 * 玩家等级区间各类型消费统计
 * @author hzy
 * 2012-8-6
 */
public class UserLevelIntervalConsumeStatsList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = -361311024955780218L;
	
	/** 所有的数据 */
	private Map<String, Integer> statsMap = new HashMap<String, Integer>();
	
	/** 测试号的数据 */
	private Map<String, Integer> testStatsMap = new HashMap<String, Integer>();
	
	@Override
	public String execute() {
		UserLevelIntervalConsumeTypeStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserLevelIntervalConsumeTypeStatsService.class);
		statsService.find(statsMap,testStatsMap);
		return SUCCESS;
	}

	/**
	 * @return the testStatsMap
	 */
	public Map<String, Integer> getTestStatsMap() {
		return testStatsMap;
	}

	/**
	 * @param testStatsMap the testStatsMap to set
	 */
	public void setTestStatsMap(Map<String, Integer> testStatsMap) {
		this.testStatsMap = testStatsMap;
	}

	/**
	 * @return the statsMap
	 */
	public Map<String, Integer> getStatsMap() {
		return statsMap;
	}

	/**
	 * @param statsMap the statsMap to set
	 */
	public void setStatsMap(Map<String, Integer> statsMap) {
		this.statsMap = statsMap;
	}
}
