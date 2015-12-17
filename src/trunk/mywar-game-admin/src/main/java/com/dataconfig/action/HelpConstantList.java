package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.FHelpConstant;
import com.dataconfig.service.HelpConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class HelpConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -8367039696987033393L;
	
	private List<FHelpConstant> helpConstantList;
	
	public String execute() {
		HelpConstantService helpConstantService = ServiceCacheFactory.getServiceCache().getService(HelpConstantService.class);
		IPage<FHelpConstant> list = helpConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			helpConstantList = (List<FHelpConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setHelpConstantList(List<FHelpConstant> helpConstantList) {
		this.helpConstantList = helpConstantList;
	}

	public List<FHelpConstant> getHelpConstantList() {
		return helpConstantList;
	}

}
