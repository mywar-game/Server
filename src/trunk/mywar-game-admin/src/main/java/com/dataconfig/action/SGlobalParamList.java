package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.SGlobalParam;
import com.dataconfig.service.SGlobalParamService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SGlobalParamList extends ALDAdminPageActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1172133886402667189L;
	private List<SGlobalParam> globalParamList;
	
	public String execute() {
		SGlobalParamService sGlobalParamService = ServiceCacheFactory.getServiceCache().getService(SGlobalParamService.class);
		IPage<SGlobalParam> page = sGlobalParamService.findGlobalParamList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (page != null) {
			globalParamList = (List<SGlobalParam>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getPageSize());
		}
		return SUCCESS;
	}

	public void setGlobalParamList(List<SGlobalParam> globalParamList) {
		this.globalParamList = globalParamList;
	}

	public List<SGlobalParam> getGlobalParamList() {
		return globalParamList;
	}
}
