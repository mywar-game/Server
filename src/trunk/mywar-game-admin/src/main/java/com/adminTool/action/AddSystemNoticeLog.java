package com.adminTool.action;

import com.adminTool.bo.SystemNoticeLog;
import com.adminTool.service.SystemNoticeLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddSystemNoticeLog extends ALDAdminActionSupport implements ModelDriven<SystemNoticeLog>{

	/** * */
	private static final long serialVersionUID = -3402322794522546530L;
	
	private SystemNoticeLog systemNoticeLog = new SystemNoticeLog();
	
	private String isCommit = "F";
	
	/** 选中的要生成激活码的服务器id **/
	private String serverIds;

	@Override
	public String execute() {
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			//循环发送邮件
			for (String serverId : serverIds.split(",")) {
				CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
				
				SystemNoticeLogService systemNoticeLogService = ServiceCacheFactory.getServiceCache().getService(SystemNoticeLogService.class);
				systemNoticeLogService.addSystemNoticeLog(systemNoticeLog);
			}
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

	/**
	 * 获取 选中的要生成激活码的服务器id 
	 */
	public String getServerIds() {
		return serverIds;
	}

	/**
	 * 设置 选中的要生成激活码的服务器id 
	 */
	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}
	
}
