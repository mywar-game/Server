package com.stats.action;

import java.util.Map;

import com.dataconfig.service.HHeroConstantService;
import com.dataconfig.service.PetConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.BattleLogService;

/**
 * 英雄和宠物出战次数统计
 * @author hzy
 * 2012-8-18
 */
public class HeroAndPetBattleTimesStatsList extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 2922142886764357115L;
	
	/**  */
	private Map<Integer, Long> statsMap;
	
	/**  */
	private Map<Integer, String> heroIdNameMap;
	
	/**  */
	private Map<Integer, String> petIdNameMap;
	
	@Override
	public String execute() throws Exception {
		HHeroConstantService heroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
		PetConstantService petConstantService = ServiceCacheFactory.getServiceCache().getService(PetConstantService.class);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		
		heroIdNameMap = heroConstantService.findHeroIdNameMap();
		petIdNameMap = petConstantService.findPetIdNameMap();
		
		statsMap = battleLogService.findHeroAndPetBattleTimes();
		return SUCCESS;
	}

	/**
	 * @return the statsMap
	 */
	public Map<Integer, Long> getStatsMap() {
		return statsMap;
	}

	/**
	 * @param statsMap the statsMap to set
	 */
	public void setStatsMap(Map<Integer, Long> statsMap) {
		this.statsMap = statsMap;
	}

	/**
	 * @return the heroIdNameMap
	 */
	public Map<Integer, String> getHeroIdNameMap() {
		return heroIdNameMap;
	}

	/**
	 * @param heroIdNameMap the heroIdNameMap to set
	 */
	public void setHeroIdNameMap(Map<Integer, String> heroIdNameMap) {
		this.heroIdNameMap = heroIdNameMap;
	}

	/**
	 * @return the petIdNameMap
	 */
	public Map<Integer, String> getPetIdNameMap() {
		return petIdNameMap;
	}

	/**
	 * @param petIdNameMap the petIdNameMap to set
	 */
	public void setPetIdNameMap(Map<Integer, String> petIdNameMap) {
		this.petIdNameMap = petIdNameMap;
	}

}
