package com.adminTool.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserOnlineLog;
import com.log.service.UserOnlineLogService;

public class UserOnlineGraph extends ALDAdminActionSupport{

	/** * */
	private static final long serialVersionUID = 1L;
	
	private List<UserOnlineLog> list = new ArrayList<UserOnlineLog>();
	
	private Date chooseDate;

	@Override
	public String execute() throws Exception {
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		if (chooseDate == null) {
			chooseDate = new Date();
		}
		System.out.println(chooseDate.getTime());
		list = userOnlineLogService.findOneDayLogList(chooseDate);
		
		return SUCCESS;
	}

	public void setList(List<UserOnlineLog> list) {
		this.list = list;
	}

	public List<UserOnlineLog> getList() {
		return list;
	}

	public void setChooseDate(Date chooseDate) {
		this.chooseDate = chooseDate;
	}

	public Date getChooseDate() {
		return chooseDate;
	}
	
}
