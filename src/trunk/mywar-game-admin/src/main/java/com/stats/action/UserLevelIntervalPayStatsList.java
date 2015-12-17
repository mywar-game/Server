package com.stats.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 玩家等级区间充值总数查询
 * @author hzy
 * 2012-4-25
 */
public class UserLevelIntervalPayStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = -6486985057769370739L;
	
	private Map<String, String> statsMap = new LinkedHashMap<String, String>();

	public String execute() {
		UserPayService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		Map<Integer, String> userIdPayAmountMap = userPayHistoryLogService.getEveryUserPayTimesAndAmountAndNowLevel();
		
		for (Integer levelIntervalIndex : userIdPayAmountMap.keySet()) {
			//等级区间
			String levelInterval;
			if (levelIntervalIndex == 0) {
				levelInterval = "1-9";
			} else {
				levelInterval = levelIntervalIndex*10 + "-" + (levelIntervalIndex + "9");
			}
			
			statsMap.put(levelInterval, userIdPayAmountMap.get(levelIntervalIndex));
		}
		return SUCCESS;
	}

	/**
	 * 获取 statsMap 
	 */
	public Map<String, String> getStatsMap() {
		return statsMap;
	}

	/**
	 * 设置 statsMap 
	 */
	public void setStatsMap(Map<String, String> statsMap) {
		this.statsMap = statsMap;
	}

}
