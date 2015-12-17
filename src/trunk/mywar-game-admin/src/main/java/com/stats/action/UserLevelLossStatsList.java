package com.stats.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserLevelupLogService;

/**
 * 玩家等级流失统计列表
 * @author hzy
 * 2012-4-18
 */
public class UserLevelLossStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = -5848758410910362511L;

	private Map<Integer, Integer> statsMap = new LinkedHashMap<Integer, Integer>();
	private String fromTime1;
	private String endTime1;
	
	

	public String getFromTime1() {
		return fromTime1;
	}

	public void setFromTime1(String fromTime1) {
		this.fromTime1 = fromTime1;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String execute() {
		UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
		if (fromTime1 != null && !fromTime1.equalsIgnoreCase("") && endTime1 != null && !endTime1.equalsIgnoreCase("")) {
			statsMap = userLevelupLogService.findLevelAndUserAmountBetweenDay(fromTime1, endTime1);
		} else {
			statsMap = userLevelupLogService.findLevelAndUserAmount();
		}		
		return SUCCESS;
	}

	public void setStatsMap(Map<Integer, Integer> statsMap) {
		this.statsMap = statsMap;
	}

	public Map<Integer, Integer> getStatsMap() {
		return statsMap;
	}
}
