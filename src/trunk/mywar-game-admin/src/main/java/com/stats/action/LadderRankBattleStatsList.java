package com.stats.action;

import java.util.Map;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.BattleLogService;

public class LadderRankBattleStatsList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;

	private Map<Integer, String> statsMap;
	
	@Override
	public String execute() throws Exception {
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		statsMap = battleLogService.findAttackNumIntervalInfoInLadderRankBattle(getStartDate(), getEndDate());
		return SUCCESS;
	}

	public void setStatsMap(Map<Integer, String> statsMap) {
		this.statsMap = statsMap;
	}

	public Map<Integer, String> getStatsMap() {
		return statsMap;
	}

}
