package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserCreateRoleLossStats;
import com.stats.service.UserCreateRoleLossStatsService;

/**
 * 玩家创建角色流失统计列表
 * @author hzy
 * 2012-4-12
 */
public class UserCreateRoleLossStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = -9159122090691893953L;

	private List<UserCreateRoleLossStats> statsList;
	
	public String execute() {
		UserCreateRoleLossStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserCreateRoleLossStatsService.class);
		
		IPage<UserCreateRoleLossStats> page = null;
		
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = statsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = statsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserCreateRoleLossStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserCreateRoleLossStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserCreateRoleLossStats> getStatsList() {
		return statsList;
	}
}
