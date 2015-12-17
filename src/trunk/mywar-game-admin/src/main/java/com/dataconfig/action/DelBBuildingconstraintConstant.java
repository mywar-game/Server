package com.dataconfig.action;

import com.dataconfig.bo.BBuildingconstraintConstant;
import com.dataconfig.service.BBuildingconstraintConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelBBuildingconstraintConstant extends ALDAdminActionSupport implements ModelDriven<BBuildingconstraintConstant> {

	/** **/
	private static final long serialVersionUID = -1200975857844859984L;
	
	/** **/
	private BBuildingconstraintConstant buildingconstraintConstant = new BBuildingconstraintConstant();
	
	public void executeDel() {
		BBuildingconstraintConstantService buildingconstraintConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingconstraintConstantService.class);
		buildingconstraintConstantService.delBBuildingconstraintConstant(buildingconstraintConstant.getBuildingConstraintId());
	}
	
	@Override
	public BBuildingconstraintConstant getModel() {
		// TODO Auto-generated method stub
		return buildingconstraintConstant;
	}

	public BBuildingconstraintConstant getBuildingconstraintConstant() {
		return buildingconstraintConstant;
	}

	public void setBuildingconstraintConstant(
			BBuildingconstraintConstant buildingconstraintConstant) {
		this.buildingconstraintConstant = buildingconstraintConstant;
	}
}
