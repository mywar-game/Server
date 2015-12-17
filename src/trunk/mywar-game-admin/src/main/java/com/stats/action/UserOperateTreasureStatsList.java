package com.stats.action;

import java.util.Map;

import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserTreasureLogService;

/**
 * 玩家道具统计列表
 * @author hzy
 * 2012-4-19
 */
public class UserOperateTreasureStatsList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1270627553903624481L;

	/**  */
	private Map<Integer, Map<String, Integer>> map;
	
	/**  */
	private Map<Integer, String> treasureIDNameMap;
	
	public String execute() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		
		UserTreasureLogService userTreasureLogService = ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		map = userTreasureLogService.findTreasureIdAndOperationAmountMap();
		return SUCCESS;
	}

	public void setMap(Map<Integer, Map<String, Integer>> map) {
		this.map = map;
	}

	public Map<Integer, Map<String, Integer>> getMap() {
		return map;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}


}
