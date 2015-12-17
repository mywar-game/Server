package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BTechnologyConstant;
import com.dataconfig.service.BTechnologyConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BTechnologyConstantList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = 8221456176839917573L;

	/** **/
	private List<BTechnologyConstant> technologyConstantList;
	
	public String execute() {
		BTechnologyConstantService technologyConstantService = ServiceCacheFactory.getServiceCache().getService(BTechnologyConstantService.class);
		IPage<BTechnologyConstant> list = technologyConstantService.findBTechnologyConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			technologyConstantList = (List<BTechnologyConstant>) list.getData();
			super.setTotalSize(list.getTotalSize());
			super.setTotalPage(list.getTotalPage());
		}
		return SUCCESS;
	}

	public List<BTechnologyConstant> getTechnologyConstantList() {
		return technologyConstantList;
	}

	public void setTechnologyConstantList(
			List<BTechnologyConstant> technologyConstantList) {
		this.technologyConstantList = technologyConstantList;
	}
}
