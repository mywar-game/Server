package com.adminTool.action; 

import com.adminTool.bo.SystemNoticeLog;
import com.adminTool.service.SystemNoticeLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateSystemNoticeLog extends ALDAdminActionSupport implements ModelDriven<SystemNoticeLog>{

	/** * */
	private static final long serialVersionUID = -6996896777741427750L; 
	
	private SystemNoticeLog systemNoticeLog = new SystemNoticeLog();
	
	private String isCommit = "F";
	
	@Override
	public String execute() {
		SystemNoticeLogService systemNoticeLogService = ServiceCacheFactory.getServiceCache().getService(SystemNoticeLogService.class); 
		if ("F".equals(isCommit)) {
			systemNoticeLog = systemNoticeLogService.getSystemNoticeLog(systemNoticeLog.getNoticeLogId());
			return INPUT;
		} else {
			systemNoticeLogService.updateSystemNoticeLog(systemNoticeLog); 
			return SUCCESS;
		}
	}

	@Override
	public SystemNoticeLog getModel() {
		return systemNoticeLog;
	}

	public void setSystemNoticeLog(SystemNoticeLog systemNoticeLog) {
		this.systemNoticeLog = systemNoticeLog;
	}

	public SystemNoticeLog getSystemNoticeLog() {
		return systemNoticeLog;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}
	
}
