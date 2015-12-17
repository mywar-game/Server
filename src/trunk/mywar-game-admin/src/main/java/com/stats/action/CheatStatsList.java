package com.stats.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.CheatStats;
import com.stats.service.CheatStatsService;

public class CheatStatsList extends ALDAdminStatsDatePageActionSupport{

	private static final long serialVersionUID = 1L;
	/** **/
	private Map<Integer, String> treasureIdNameMap;
	
	private Map<Integer, String> heroIdNameMap;
	
	private Map<Integer, String> equipIdNameMap;
	
	private Map<Integer, String> artifactIdNameMap;
	
	List<CheatStats> statsList = new ArrayList<CheatStats>();

	@Override
	public String execute() {
		
		CheatStatsService statsService = ServiceCacheFactory.getServiceCache().getService(CheatStatsService.class);
		IPage<CheatStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = statsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = statsService.findList(super.getPageSize(), super.getToPage());
		}
		if (page != null) {
			statsList = (List<CheatStats>)page.getData();
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);

			treasureIdNameMap = treasureConstantService.findTreasureIdNameMap(); // 道具
			heroIdNameMap = treasureConstantService.findSystemHeroIdNameMap(); // 英雄列表
			equipIdNameMap = treasureConstantService.findSystemEquipIdNameMap(); // 装备
			artifactIdNameMap = treasureConstantService.findSystemArtifactIdNameMap(); // 神器
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
//		for (CheatStats c : statsList) {
//			ToolTypeConstant typeConstant = new ToolTypeConstant();
//			c.setToolDesc(typeConstant.getToolTypeAndName(c.getTreasureType() + "," + c.getTreasureId() + ","));		
//		}
		return SUCCESS;
	}
	
	public Map<Integer, String> getTreasureIdNameMap() {
		return treasureIdNameMap;
	}

	public void setTreasureIdNameMap(Map<Integer, String> treasureIdNameMap) {
		this.treasureIdNameMap = treasureIdNameMap;
	}

	public Map<Integer, String> getHeroIdNameMap() {
		return heroIdNameMap;
	}

	public void setHeroIdNameMap(Map<Integer, String> heroIdNameMap) {
		this.heroIdNameMap = heroIdNameMap;
	}

	public Map<Integer, String> getEquipIdNameMap() {
		return equipIdNameMap;
	}

	public void setEquipIdNameMap(Map<Integer, String> equipIdNameMap) {
		this.equipIdNameMap = equipIdNameMap;
	}

	public Map<Integer, String> getArtifactIdNameMap() {
		return artifactIdNameMap;
	}

	public void setArtifactIdNameMap(Map<Integer, String> artifactIdNameMap) {
		this.artifactIdNameMap = artifactIdNameMap;
	}

	public List<CheatStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<CheatStats> statsList) {
		this.statsList = statsList;
	}
}
