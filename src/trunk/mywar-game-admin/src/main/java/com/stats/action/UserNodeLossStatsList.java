package com.stats.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.AActionConstant;
import com.dataconfig.service.AActionConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.service.UserNodeLossStatsService;

/**
 * 玩家节点流失统计列表
 * @author hzy
 * 2012-4-18
 */
public class UserNodeLossStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 6896309798497256522L;

	private Map<Integer, Integer> statsMap = new LinkedHashMap<Integer, Integer>();
	private List<AActionConstant> actionList;
	public String execute() {
		UserNodeLossStatsService userNodeLossStatsService = ServiceCacheFactory.getServiceCache().getService(UserNodeLossStatsService.class);
		AActionConstantService actionConstantService = ServiceCacheFactory.getServiceCache().getService(AActionConstantService.class);
		actionList = actionConstantService.findAActionConstantList();
		statsMap = userNodeLossStatsService.findActionIdAndUserAmount(super.getStartDate(),super.getEndDate());
		return SUCCESS;
	}

	public Map<Integer, Integer> getStatsMap() {
		return statsMap;
	}

	public void setStatsMap(Map<Integer, Integer> statsMap) {
		this.statsMap = statsMap;
	}

	public List<AActionConstant> getActionList() {
		return actionList;
	}

	public void setActionList(List<AActionConstant> actionList) {
		this.actionList = actionList;
	}
}
