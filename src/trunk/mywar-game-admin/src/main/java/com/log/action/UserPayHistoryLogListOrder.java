package com.log.action;

import java.util.ArrayList;
import java.util.List;

import com.admin.util.Tools;
import com.dataconfig.bo.PaymentLog;
import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class UserPayHistoryLogListOrder extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String isCommit = "F";
	private List<PaymentLog> userPayHistoryList = new ArrayList<PaymentLog>();
	
	@Override
	public String execute() throws Exception {
		UserPayService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		if(isCommit.equals("T")){
			IPage<PaymentLog> list = userPayHistoryLogService.findUserPayHistoryLogListByCondition(super.getLodoId(),super.getStartDate(),super.getEndDate(), super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			if (list != null) {
				userPayHistoryList = (List<PaymentLog>) list.getData();
				
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
