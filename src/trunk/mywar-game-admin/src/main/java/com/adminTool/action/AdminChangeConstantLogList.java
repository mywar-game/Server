package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AdminChangeConstantLogList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = -8059972810524139531L;
	
	private List<AdminChangeConstantLog> list;
	
	public String execute() throws Exception {
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
		
		IPage<AdminChangeConstantLog> pageList = adminChangeConstantLogService.findPageList(super.getPageSize(), super.getToPage());
		if (pageList != null) {
			list = (List<AdminChangeConstantLog>) pageList.getData();
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}
	
	public void setList(List<AdminChangeConstantLog> list) {
		this.list = list;
	}
	
	public List<AdminChangeConstantLog> getList() {
		return list;
	}
	
}
