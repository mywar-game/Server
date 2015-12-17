package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.PayUserInfoStats;
import com.stats.service.PayUserInfoStatsService;

public class PayUserInfoStatsList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = 8628068813597110080L;

	private List<PayUserInfoStats> statsList;
	
	public String execute(){
		PayUserInfoStatsService statsService = ServiceCacheFactory.getServiceCache().getService(PayUserInfoStatsService.class);
		IPage<PayUserInfoStats> page = statsService.findList(DEFAULT_PAGESIZE, super.getToPage());

		if (page != null) {
			statsList = (List<PayUserInfoStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<PayUserInfoStats> statsList) {
		this.statsList = statsList;
	}

	public List<PayUserInfoStats> getStatsList() {
		return statsList;
	}
}
