package com.log.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.dataconfig.bo.PaymentLog;
import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class UserPayHistoryList extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String isCommit = "F";
	private List<PaymentLog> userPayHistoryList = new ArrayList<PaymentLog>();
	
	@Override
	public String execute() throws Exception {
		UserPayService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();
		if(isCommit.equals("T")){
			IPage<PaymentLog> list = userPayHistoryLogService.findUserPayHistoryLogListByCondition(super.getLodoId(),super.getStartDate(),super.getEndDate(), super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			if (list != null) {
				userPayHistoryList = (List<PaymentLog>) list.getData();
				
				if (userPayHistoryList != null) {
					for (PaymentLog log : userPayHistoryList) {
						String partnerId = log.getPartnerId();
						Partner partner = partnerMap.get(partnerId);
						if (partner == null) {
							log.setPartnerName("未命名渠道号");
						} else {
							log.setPartnerName(partnerMap.get(partnerId).getPName());
						}
					}
				}
				//如果没有数据看玩家是否存在
				if (userPayHistoryList == null || userPayHistoryList.size() == 0) {
					super.searchUser();
					if (!Tools.isEmpty(super.getErroDescrip())) {
						return SUCCESS;
					}
				}
				super.setTotalPage(list.getTotalPage());
				super.setTotalSize(list.getTotalSize());
			}
		}
		
		return SUCCESS;
	}

	public List<PaymentLog> getUserPayHistoryList() {
		return userPayHistoryList;
	}

	public void setUserPayHistoryList(List<PaymentLog> userPayHistoryList) {
		this.userPayHistoryList = userPayHistoryList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
