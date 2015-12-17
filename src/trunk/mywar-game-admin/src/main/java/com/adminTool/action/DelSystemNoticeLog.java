package com.adminTool.action;

import com.adminTool.bo.SystemNoticeLog;
import com.adminTool.service.SystemNoticeLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 删除公告
 * @author ws
 *
 */
public class DelSystemNoticeLog extends ALDAdminActionSupport implements ModelDriven<SystemNoticeLog> {

	/** * */
	private static final long serialVersionUID = 6277000733352759496L;

	private SystemNoticeLog systemNoticeLog = new SystemNoticeLog();
	
	public void executeDel() {
		SystemNoticeLogService systemNoticeLogService = ServiceCacheFactory.getServiceCache().getService(SystemNoticeLogService.class);
		systemNoticeLogService.delSystemNoticeLog(systemNoticeLog.getNoticeLogId());
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

}
