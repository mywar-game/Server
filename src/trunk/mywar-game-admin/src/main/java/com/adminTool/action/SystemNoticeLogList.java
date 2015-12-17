package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.SystemNoticeLog;
import com.adminTool.service.SystemNoticeLogService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SystemNoticeLogList extends ALDAdminPageActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4110939076776064392L;
	
	private List<SystemNoticeLog> systemNoticeLogList;

	public String execute() {
		SystemNoticeLogService systemNoticeLogService = ServiceCacheFactory.getServiceCache().getService(SystemNoticeLogService.class);
		IPage<SystemNoticeLog> list = systemNoticeLogService.findAllSystemNoticeLog(super.getToPage(), 100);
		if (list != null) {
			systemNoticeLogList = (List<SystemNoticeLog>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
		
	}
	
	public List<SystemNoticeLog> getSystemNoticeLogList() {
		return systemNoticeLogList;
	}
	
	public void setSystemNoticeLogList(List<SystemNoticeLog> systemNoticeLogList) {
		this.systemNoticeLogList = systemNoticeLogList;
	}
	
}
