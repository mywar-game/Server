package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BBuildingConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BBuildingConstantList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = -4533132928034732433L;
	
	/** **/
	private List<BBuildingConstant> buildingConstantList;
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		IPage<BBuildingConstant> list = buildingConstantService.findBBuildingConstantPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			buildingConstantList = (List<BBuildingConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public List<BBuildingConstant> getBuildingConstantList() {
		return buildingConstantList;
	}

	public void setBuildingConstantList(List<BBuildingConstant> buildingConstantList) {
		this.buildingConstantList = buildingConstantList;
	} 
}
