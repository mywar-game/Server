package com.log.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.dataconfig.bo.PaymentLog;
import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class UserPayLogList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = 1300296781505140912L;
	private List<PaymentLog> userPayHistoryList = new ArrayList<PaymentLog>();

	@Override
	public String execute() throws Exception {
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		UserPayService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();
		IPage<PaymentLog> list = userPayHistoryLogService.findUserPayHistoryLogList(super.getStartDate(), super.getEndDate(), super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			userPayHistoryList = (List<PaymentLog>) list.getData();
			
			for (PaymentLog log : userPayHistoryList) {
				String partnerId = log.getPartnerId();
				Partner partner = partnerMap.get(partnerId);
				if (partner == null) {
					log.setPartnerName("未命名渠道号");
				} else {
					log.setPartnerName(partnerMap.get(partnerId).getPName());
				}
			}
			
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		
		return SUCCESS;
	}
	
	public List<PaymentLog> getUserPayHistoryList() {
		return userPayHistoryList;
	}

	public void setUserPayHistoryList(List<PaymentLog> userPayHistoryList) {
		this.userPayHistoryList = userPayHistoryList;
	}
}
