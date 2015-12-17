package com.stats.action;

import java.util.List;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.PaymentLogStats;
import com.stats.service.UserPaymentLogStatsService;

public class UserPayLogStatsList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = 2208016217700682822L;
	@SuppressWarnings("rawtypes")
	private List userPayHistoryListStats ;
	
	
	public String execute() throws Exception {
		
		UserPaymentLogStatsService userPayStatsService = ServiceCacheFactory.getServiceCache().getService(UserPaymentLogStatsService.class);
		
			
			IPage<PaymentLogStats> list = userPayStatsService.findPaymentLogStatsPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			userPayHistoryListStats = (List<PaymentLogStats>) list.getData();
			
			
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		
		
		return SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public List getUserPayHistoryListOrder() {
		return userPayHistoryListStats;
	}

	@SuppressWarnings("rawtypes")
	public void setUserPayHistoryListOrder(List userPayHistoryListOrder) {
		this.userPayHistoryListStats = userPayHistoryListOrder;
	}
}
