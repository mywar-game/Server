package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;
import com.adminTool.bo.GlobalInfo;
import com.adminTool.bo.StatisticsInfo;
import com.adminTool.service.GlobalService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class GetGlobalInfo extends ALDAdminActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StatisticsInfo statisticsInfo = new StatisticsInfo();
	
	private List<GlobalInfo> list = new ArrayList<GlobalInfo>();
	
	@Override
	public String execute() {
		
		GlobalService globalService = ServiceCacheFactory.getServiceCache().getService(GlobalService.class);
		String sysNum = this.getAdminUser().getExp1();
		
		// 从游戏服务器获取世界总览信息
		list = globalService.findAllGlobalInfoBySysNum(Integer.valueOf(sysNum));
		List<StatisticsInfo> tempList = globalService.findAllStatisticsInfoBySysNum(Integer.valueOf(sysNum));
		if (tempList != null && tempList.size() > 0) {
			statisticsInfo = tempList.get(0);
		}
		return SUCCESS;
	}

	public StatisticsInfo getStatisticsInfo() {
		return statisticsInfo;
	}

	public void setStatisticsInfo(StatisticsInfo statisticsInfo) {
		this.statisticsInfo = statisticsInfo;
	}

	public List<GlobalInfo> getList() {
		return list;
	}

	public void setList(List<GlobalInfo> list) {
		this.list = list;
	}
	
}
