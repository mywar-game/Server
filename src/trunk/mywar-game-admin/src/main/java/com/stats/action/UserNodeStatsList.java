package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.dataconfig.bo.AActionConstant;
import com.dataconfig.service.AActionConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserActionLogService;
import com.stats.bo.UserNodeStats;

/**
 * 玩家节点统计列表
 * 
 * @author yezp
 */
public class UserNodeStatsList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = 7364533141523373979L;

	private List<UserNodeStats> nodeStatsList;
	private String isCommit = "F";
	private Integer actionType;

	public String execute() {
		if (isCommit.equals("F")) {
			return SUCCESS;
		}

		AActionConstantService actionConstantService = ServiceCacheFactory
				.getServiceCache().getService(AActionConstantService.class);
		UserActionLogService logService = ServiceCacheFactory.getServiceCache()
				.getService(UserActionLogService.class);

		// 根据买点类型查找买点
		List<AActionConstant> actionList = actionConstantService
				.findAActionConstantListByType(actionType);

		nodeStatsList = new ArrayList<UserNodeStats>();
		for (AActionConstant aAction : actionList) {
			UserNodeStats userNodeStats = logService
					.statsActionLog(super.getStartDate(),
							super.getEndDate(), aAction.getActionId());
			userNodeStats.setActionDesc(aAction.getActionDesc());
			nodeStatsList.add(userNodeStats);
		}

		return SUCCESS;
	}

	public List<UserNodeStats> getNodeStatsList() {
		return nodeStatsList;
	}

	public void setNodeStatsList(List<UserNodeStats> nodeStatsList) {
		this.nodeStatsList = nodeStatsList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

}
