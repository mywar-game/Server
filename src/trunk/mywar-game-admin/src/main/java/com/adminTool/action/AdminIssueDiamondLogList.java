package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.AdminIssueDiamondLog;
import com.adminTool.service.AdminIssueDiamondLogService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AdminIssueDiamondLogList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -1916669822762686844L;
	private List<AdminIssueDiamondLog> list;

	public String execute() throws Exception {
		AdminIssueDiamondLogService adminIssueDiamondLogService = ServiceCacheFactory
				.getServiceCache()
				.getService(AdminIssueDiamondLogService.class);

		IPage<AdminIssueDiamondLog> pageList = adminIssueDiamondLogService
				.findPageList(DEFAULT_PAGESIZE, super.getToPage());
		if (pageList != null) {
			list = (List<AdminIssueDiamondLog>) pageList.getData();

			for (AdminIssueDiamondLog adminIssueDiamondLog : list) {
				adminIssueDiamondLog.setIssueReason(adminIssueDiamondLog
						.getIssueReason().replaceAll("\\n", "<br>"));
			}

			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}

	public List<AdminIssueDiamondLog> getList() {
		return list;
	}

	public void setList(List<AdminIssueDiamondLog> list) {
		this.list = list;
	}
}
