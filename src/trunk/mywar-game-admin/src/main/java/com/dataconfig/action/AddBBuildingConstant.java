package com.dataconfig.action;

import com.dataconfig.bo.BBuildingConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddBBuildingConstant extends ALDAdminActionSupport implements ModelDriven<BBuildingConstant> {

	/** **/
	private static final long serialVersionUID = 7514315920490781361L;

	/** **/
	private BBuildingConstant buildingConstant = new BBuildingConstant();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return INPUT;
		}
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingConstantService.addBBuildingConstant(buildingConstant);
		return SUCCESS;
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
