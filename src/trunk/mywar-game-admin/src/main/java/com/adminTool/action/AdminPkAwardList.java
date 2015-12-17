package com.adminTool.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminPkAward;
import com.adminTool.service.AdminPkAwardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;


/**
 * Pk奖励列表
 * 
 * 
 */
public class AdminPkAwardList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -7118088932127959338L;

	private List<AdminPkAward> pkAwardList;

	public String execute() {
		AdminPkAwardService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminPkAwardService.class);

		IPage<AdminPkAward> iPage = service.findPkAwardPageList(
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if (iPage == null) {
			pkAwardList = new ArrayList<AdminPkAward>();
			return SUCCESS;
		}

		pkAwardList = (List<AdminPkAward>) iPage.getData();

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<AdminPkAward> getPkAwardList() {
		return pkAwardList;
	}

	public void setPkAwardList(List<AdminPkAward> pkAwardList) {
		this.pkAwardList = pkAwardList;
	}

}
