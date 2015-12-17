package com.dataconfig.action;

import com.dataconfig.bo.BBuildingConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelBBuildingConstant extends ALDAdminActionSupport implements ModelDriven<BBuildingConstant> {

	/** **/
	private static final long serialVersionUID = -8967645744018673706L;

	/** **/
	private BBuildingConstant buildingConstant = new BBuildingConstant();
	
	public void executeDel() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingConstantService.delBBuildingConstant(buildingConstant.getBuildingId());
	}
	
	@Override
	public BBuildingConstant getModel() {
		// TODO Auto-generated method stub
		return buildingConstant;
	}

	public BBuildingConstant getBuildingConstant() {
		return buildingConstant;
	}

	public void setBuildingConstant(BBuildingConstant buildingConstant) {
		this.buildingConstant = buildingConstant;
	}
}
