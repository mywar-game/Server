package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BTechnologyOpenConstant;
import com.dataconfig.service.TechnologyOpenConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class TechnologyOpenConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 5625641885479220645L;
	
	private List<BTechnologyOpenConstant> technologyOpenConstantList;
	
	public String execute() {
		TechnologyOpenConstantService technologyOpenConstantService = ServiceCacheFactory.getServiceCache().getService(TechnologyOpenConstantService.class);
		IPage<BTechnologyOpenConstant> list = technologyOpenConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			technologyOpenConstantList = (List<BTechnologyOpenConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setTechnologyOpenConstantList(
			List<BTechnologyOpenConstant> technologyOpenConstantList) {
		this.technologyOpenConstantList = technologyOpenConstantList;
	}

	public List<BTechnologyOpenConstant> getTechnologyOpenConstantList() {
		return technologyOpenConstantList;
	}

}
